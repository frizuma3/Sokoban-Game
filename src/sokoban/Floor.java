package sokoban;

public class Floor extends MapElement {
   Floor(){ 
       setSymbol ("F");
       setImgFilename ("/graphics/floor.jpg");
                   
   }   
}







//public class Floor {
//  static Scanner kboard = new Scanner(System.in);
//    public void readFile(){  
//    	try { 
//			File myObj = new File("floor.jpg");
//			Scanner myReader = new Scanner(myObj);	
//			while (myReader.hasNextLine()) { 
//				String data = myReader.nextLine(); 
//				System.out.println(data); 
//			} 
//			myReader.close(); 
//
//		 } catch (IOException e){
//                  e.printStackTrace();
//                 } finally {
//                    System.out.println("Finished Reading floor");
//        }
//
//    }
//}









//public class Floor {
//    public void readFile(){
//   //public static void main(String[] args) {
//      File file = new File("floor.jpg"); // Specify the path to the image file
//      try {
//         BufferedImage image = ImageIO.read(file);
//         
//         System.out.println("Image width: " );
//         System.out.println("Image height: " );
//      } catch (IOException e) {
//         e.printStackTrace();
//      }
//   }
//}