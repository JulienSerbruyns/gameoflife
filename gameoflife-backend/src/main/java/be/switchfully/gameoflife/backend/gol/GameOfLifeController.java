package be.switchfully.gameoflife.backend.gol;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = GameOfLifeController.WORLD_BASE_URL)
public class GameOfLifeController {

    static final String WORLD_BASE_URL = "/api/gol";
    private static Logger logger = Logger.getLogger(GameOfLifeController.class);
    private List<List<Boolean>> currentWorld;
    private List<List<Boolean>> nextWorld;

    @PostMapping(value = "/phase")
    public List<List<Boolean>> nextGeneration(@RequestBody List<List<Boolean>> currentWorld) {
        int worldSize = currentWorld.size();
        this.currentWorld = currentWorld;
        nextWorld = new ArrayList<>();
        nextWorld.addAll(currentWorld);
        for (int x = 0; x < worldSize-1; x++) {
            for (int y = 0; y < worldSize-1; y++) {
                int adjacentCells = countAdjacentLivingCells(x, y);
                computeIfLiveOrDie(x, y,adjacentCells);
            }
        }
        return nextWorld;
    }


    private void computeIfLiveOrDie(int x, int y,int adjacentCells) {
        if (!currentWorld.get(x).get(y) && adjacentCells == 3) {
            nextWorld.get(x).set(y,true);
        }
        if (currentWorld.get(x).get(y) && adjacentCells < 2) {
            nextWorld.get(x).set(y,false);
        }
        if (currentWorld.get(x).get(y) && adjacentCells > 3) {
            nextWorld.get(x).set(y,false);
        }
        if (currentWorld.get(x).get(y) && (adjacentCells == 2 || adjacentCells == 3)) {
            nextWorld.get(x).set(y,true);
        }
    }

    private int countAdjacentLivingCells(int x, int y) {
        int adjacentLivingCells = 0;
        if (x == 0 && y > 0) {
            for (int posX = x ; posX <= x + 1; posX++) {
                for (int posY = y - 1; posY <= y + 1; posY++) {
                    if (currentWorld.get(posX).get(posY) && posX != x && posY != y) {
                        adjacentLivingCells++;
                    }
                }
            }
        }
        else if(x > 0 && y ==0) {
            for (int posX = x - 1; posX <= x + 1; posX++) {
                for (int posY = y ; posY <= y + 1; posY++) {
                    if (currentWorld.get(posX).get(posY) && posX != x && posY != y) {
                        adjacentLivingCells++;
                    }
                }
            }
        }
        else if(x > 0 && y == currentWorld.size()-1) {
            for (int posX = x - 1; posX <= x + 1; posX++) {
                for (int posY = y ; posY <= y - 1; posY++) {
                    if (currentWorld.get(posX).get(posY) && posX != x && posY != y) {
                        adjacentLivingCells++;
                    }
                }
            }
        }
        else if(x == currentWorld.size()-1  && y > 0) {
            for (int posX = x -1; posX <= x ; posX++) {
                for (int posY = y -1 ; posY <= y + 1; posY++) {
                    if (currentWorld.get(posX).get(posY) && posX != x && posY != y) {
                        adjacentLivingCells++;
                    }
                }
            }
        }
       else if (x == 0 && y==0) {
            for (int posX = x; posX <= x + 1; posX++) {
                for (int posY = y; posY <= y + 1; posY++) {
                    if (currentWorld.get(posX).get(posY) && posX != x && posY != y) {
                        adjacentLivingCells++;
                    }
                }
            }
        }
        else if (x == 0 && y==currentWorld.size()-1) {
            for (int posX = x; posX <= x + 1; posX++) {
                for (int posY = y-1; posY <= y; posY++) {
                    if (currentWorld.get(posX).get(posY) && posX != x && posY != y) {
                        adjacentLivingCells++;
                    }
                }
            }
        }
        else if (x == currentWorld.size()-1 && y==0) {
            for (int posX = x-1; posX <= x; posX++) {
                for (int posY = y; posY <= y + 1; posY++) {
                    if (currentWorld.get(posX).get(posY) && posX != x && posY != y) {
                        adjacentLivingCells++;
                    }
                }
            }
        }
        else if (x == currentWorld.size()-1 && y==currentWorld.size()-1) {
            for (int posX = x-1; posX <= x; posX++) {
                for (int posY = y-1; posY <= y; posY++) {
                    if (currentWorld.get(posX).get(posY) && posX != x && posY != y) {
                        adjacentLivingCells++;
                    }
                }
            }
        }
        else {

            for (int posX = x - 1; posX <= x + 1; posX++) {
                for (int posY = y - 1; posY <= y + 1; posY++) {
                    if (currentWorld.get(posX).get(posY) && posX != x && posY != y) {
                        adjacentLivingCells++;
                    }
                }
            }
        }
        return adjacentLivingCells;
    }



//    private Boolean[][] convertToArray(List<List<Boolean>> current) {
//        int size = current.size();
//        Boolean[][] resultArray = new Boolean[size][size];
//        for (int x = 0; x < size; x++) {
//            for (int y = 0; y < size; y++) {
//                resultArray[x][y] = current.get(x).get(y);
//            }
//        }
//
//        return resultArray;
//    }



}
