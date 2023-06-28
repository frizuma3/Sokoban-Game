
package sokoban;



public class Crate extends MapElement{
  Crate(){ 
       setSymbol ("C");
       setImgFilename ("/graphics/crate.jpg"); 
       setCanBePushed (true);
   }  
}
  









//  static Scanner kboard = new Scanner(System.in);
//    public void readFile(){  
//    
//    	try { 
//			File myObj = new File("crate.jpg"); 
//			Scanner myReader = new Scanner(myObj); 
//				
//			while (myReader.hasNextLine()) { 
//				String data = myReader.nextLine(); 
//				System.out.println(data); 
//			} 
//			myReader.close(); 
//
//		 } catch (IOException e){
//                  e.printStackTrace();
//                 } finally {
//                    System.out.println("Finished Reading");
//        }
//
//    }  
