package jgc.Game;

public interface GameCommon {
    int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    int TARGET_FPS = 60; //Target FPS to reach
    long OPTIMAL_TIME =  1000000000L / TARGET_FPS;
}
