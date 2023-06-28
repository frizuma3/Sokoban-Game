
package sokoban;


public class Tnt extends MapElement {
    
    Tnt(){ 
       setSymbol ("T");
       setImgFilename ("/graphics/tnt.jpg"); 
       setCanBePushed (false);
       
       setIsDestination(true); 
   }   
}
