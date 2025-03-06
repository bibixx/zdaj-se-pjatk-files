const { execSync } = require("child_process");
const fs = require("fs");
const { join } = require("path");

function buildPathTree(paths) {
  const root = {
    files: [],
    subdirectories: {},
  };

  paths.forEach((path) => {
    const parts = path.split("/").filter((p) => p !== "");
    parts.shift();
    let currentLevel = root;

    for (let i = 0; i < parts.length; i++) {
      const part = parts[i];

      if (i === parts.length - 1) {
        currentLevel.files.push(part);
      } else {
        if (!currentLevel.subdirectories[part]) {
          currentLevel.subdirectories[part] = {
            files: [],
            subdirectories: {},
          };
        }
        currentLevel = currentLevel.subdirectories[part];
      }
    }
  });

  // Clean up empty subdirectories
  function cleanupEmptySubdirs(node) {
    if (Object.keys(node.subdirectories).length === 0) {
      delete node.subdirectories;
    } else {
      for (const key in node.subdirectories) {
        cleanupEmptySubdirs(node.subdirectories[key]);
      }
    }
    return node;
  }

  return cleanupEmptySubdirs(root);
}

function main() {
  try {
    const input = execSync(
      "find . \\( -path './_meta/*' -o -path './.git/*' \\) -prune -o -type f -print -mindepth 2"
    ).toString();

    const paths = input
      .split(/\r?\n/)
      .map((line) => line.trim())
      .filter((line) => line.length > 0);

    const tree = buildPathTree(paths);

    // Write formatted JSON
    fs.writeFileSync(
      join(__dirname, "../files.json"),
      JSON.stringify(tree, null, 2)
    );

    // Write minified JSON
    fs.writeFileSync(
      join(__dirname, "../files.min.json"),
      JSON.stringify(tree)
    );

    console.log("Successfully generated:\n- files.json\n- files.min.json");
  } catch (error) {
    console.error("Error:", error.message);
    process.exit(1);
  }
}

main();
