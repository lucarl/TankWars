package com.mygdx.game.model;
import java.util.Random;

public class Terrain {
    public static final int WIDTH = 512;
    public static final int HEIGHT = 250;
    public static final String imageSource = "Terrain";
    private int displace;
    private int roughness;

    public Terrain(int displace, int roughness) {
        this.displace = displace;
        this.roughness = roughness;
    }



    /*int[] drawTerrain(int displace, int roughness) {
            int[] point;






    }*/


}
