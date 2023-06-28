package sokoban;


public class Player extends MapElement {
   Player(){ 
       setSymbol ("P");
       setImgFilename ("/graphics/player.jpg");
                   
   }   
}






//public class Player {
//  static Scanner kboard = new Scanner(System.in);
//    public void readFile(){
//        InputStreamReader myReader = new InputStreamReader(getClass().getResourceAsStream("/map/Map0.map"));
//        BufferedReader buffer = new BufferedReader(myReader); 
//        String line;
//        
//        try{
//            while((line = buffer.readLine()) != null){
//                for (int i = 0; i < line.length(); i++) {
//                    System.out.println(line.substring(i, i+1));
//                }
//            }  
//        } catch (IOException e){
//            e.printStackTrace();
//        } finally {
//            System.out.println("Finished Reading player");
//        }
//    }
//  }
   







// public void readFile() {	
	
//  //code to read the array from the file
//		try { 
//			File myObj = new File("player.jpg"); 
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
 
 
 
 
//     public static void main(String[] args) {
//      Player pl = new Player();
//      pl.readFile();
      
     // Floor fl = new Floor();
      //fl.readFile();

