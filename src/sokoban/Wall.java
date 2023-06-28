package sokoban;



public class Wall extends MapElement {
   Wall(){ 
       setSymbol ("W");
       setImgFilename ("/graphics/wall.jpg");
       setObs (true);
       setCanBePushed (false);
       setX('x');
       setY('y');
      
       
   }   
}

//  static Scanner kboard = new Scanner(System.in);
//    public void readFile(){  
//    
//    	try { 
//			File myObj = new File("wall.jpg"); 
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
