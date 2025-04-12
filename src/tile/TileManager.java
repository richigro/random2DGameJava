package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;
    int[][] mapTileNum;
    int tileTypes;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.tileTypes = 38; // 38 Because there are 38 types of tiles available right now
        this.tiles = new Tile[tileTypes];
        mapTileNum = new int[gp.maxScreenRow][gp.maxScreenCol];
        getTileImage();
        loadMap("/maps/map001.txt");
    }

    public void getTileImage() {
        try {
            // Load all the images for each tile type
            for(int tileType = 0; tileType < tileTypes; tileType++) {
                tiles[tileType] = new Tile();
                String paddedTileType =  String.format("%03d", tileType); // pad tile type to match file name
                tiles[tileType].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+paddedTileType+".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
            try {
                InputStream is = getClass().getResourceAsStream(filePath);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                for(int row = 0; row < gp.maxScreenRow; row++) {
                    // read map file line by line
                    String line = br.readLine();
                    String[] tileTypes = line.split(" "); // All tile in each map should be separated by a space
                    // ex: { 1,0,1,1,0,2,2,2,2,2,0,1,1,0,0,1}
                    for(int col = 0; col < gp.maxScreenCol; col++) {
                        int currTileType = Integer.parseInt(tileTypes[col]);
                        // set the mapTile
                        mapTileNum[row][col] = currTileType;
                    }
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void draw(Graphics2D g2) {
        int x = 0;
        int y = 0;

        for(int row = 0; row < mapTileNum.length; row++) {
            int[] currTileRow = mapTileNum[row];
            for(int col = 0; col < currTileRow.length; col++) {
                int currTile = mapTileNum[row][col];
                // draw current tile
                g2.drawImage(tiles[currTile].image, x, y, gp.tileSize, gp.tileSize, null);
                x += gp.tileSize;
            }
            x = 0;
            y += gp.tileSize;
        }
    }
}
