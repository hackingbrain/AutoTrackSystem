/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sijie
 */
public class Album {
    private int albumID;
    private String albumName;
    private double albumPrice;
    private String albumCond;
    private String albumDesc;
    private int albumUPC;
    private int cID;

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }
    
    public Album(int albumID, String albumName, double albumPrice, 
            String albumCond, String albumDesc, int albumUPC){
        this.albumID = albumID;
        this.albumName = albumName;
        this.albumPrice = albumPrice;
        this.albumCond = albumCond;
        this.albumDesc = albumDesc;
        this.albumUPC = albumUPC;
    
    }
    
    public Album(){}
    
    
    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public double getAlbumPrice() {
        return albumPrice;
    }

    public void setAlbumPrice(double albumPrice) {
        this.albumPrice = albumPrice;
    }

    public String getAlbumCond() {
        return albumCond;
    }

    public void setAlbumCond(String albumCond) {
        this.albumCond = albumCond;
    }

    public String getAlbumDesc() {
        return albumDesc;
    }

    public void setAlbumDesc(String albumDesc) {
        this.albumDesc = albumDesc;
    }

    public int getAlbumUPC() {
        return albumUPC;
    }

    public void setAlbumUPC(int albumUPC) {
        this.albumUPC = albumUPC;
    }

    
    

    
}
