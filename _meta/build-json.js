const { execSync } = require("child_process");
const fs = require("fs");
const { join } = require("path");

function buildPathTree(partsArray) {
  const root = {
    files: [],
    subdirectories: {},
  };

  partsArray.forEach((parts) => {
    if (parts.length < 2) {
      return;
    }

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

function sortObjectKeys(obj) {
  if (Array.isArray(obj)) {
    return obj.map(sortObjectKeys).sort();
  }

  if (obj && typeof obj === "object") {
    return Object.keys(obj)
      .sort()
      .reduce((sorted, key) => {
        sorted[key] = sortObjectKeys(obj[key]);
        return sorted;
      }, {});
  }

  return obj;
}

function main() {
  try {
    const ignoreFolders = ["_meta", ".git", "node_modules", "dist", ".github", ".wrangler"];
    let input = "";

    if (process.env.CI) {
      input = execSync(`git -c core.quotePath=false ls-tree -r HEAD --name-only`).toString();
    } else {
      input = execSync(`find * -type f -print`).toString();
    }

    const parts = input
      .split(/\r?\n/)
      .map((line) => line.trim())
      .filter((line) => line.length > 0)
      .map((line) => line.split("/").filter((p) => p !== ""))
      .filter((parts) => !ignoreFolders.includes(parts[0]));

    const tree = buildPathTree(parts);
    const sortedTree = sortObjectKeys(tree);
    const distDir = join(__dirname, "../dist");

    // Create dist directory if it doesn't exist
    if (!fs.existsSync(distDir)) {
      fs.mkdirSync(distDir);
    }

    // Write formatted JSON
    fs.writeFileSync(join(__dirname, "../dist/files.json"), JSON.stringify(sortedTree, null, 2));

    // Write minified JSON
    fs.writeFileSync(join(__dirname, "../dist/files.min.json"), JSON.stringify(sortedTree));

    console.log("Successfully generated:\n- files.json\n- files.min.json");
  } catch (error) {
    console.error("Error:", error.message);
    process.exit(1);
  }
}

main();
