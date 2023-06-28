package sokoban;

public class MapElement {
    private String symbol;
    private String imgFilename;
    private boolean canBePushed;
    private boolean isDestination;
    private boolean obs;
    private MapElement underneath;
    private int x;
    private int y;
    
   

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getImgFilename() {
        return imgFilename;
    }

    public void setImgFilename(String imgFilename) {
        this.imgFilename = imgFilename;
    }

    public boolean isCanBePushed() {
        return canBePushed;
    }

    public void setCanBePushed(boolean canBePushed) {
        this.canBePushed = canBePushed;
    }

    public boolean isIsDestination() {
        return isDestination;
    }

    public void setIsDestination(boolean isDestination) {
        this.isDestination = isDestination;
    }

    public boolean isObs() {
        return obs;
    }

    public void setObs(boolean obs) {
        this.obs = obs;
    }

    public MapElement getUnderneath() {
        return underneath;
    }

    public void setUnderneath(MapElement underneath) {
        this.underneath = underneath;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
