
package sokoban;



public class Diamond extends MapElement {

    
    Diamond(){ 
       setSymbol ("D");
       setImgFilename ("/graphics/diamond.jpg"); 
       setCanBePushed (true);
     
   }
}









//  static Scanner kboard = new Scanner(System.in);
//    public void readFile(){  
//    
//    	try { 
//			File myObj = new File("diamond.jpg"); 
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
//}
