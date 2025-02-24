class TocTest {

  public static void main(String[] args) {
    final String ls = System.getProperty("line.separator");
    StringBuffer doc = new StringBuffer();
    try {
     FileReader fr = new FileReader(args[0]);
     BufferedReader br = new BufferedReader(fr);
     String line;
     while ((line = br.readLine()) != null) doc.append(line).append('\n');
     br.close();
    } catch (Exception exc) { System.out.println(exc); System.exit(1); }
   
    System.out.println(new Toc(doc.toString()).getToc());
  } 
}