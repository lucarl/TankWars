package com.mygdx.game.model;

import java.util.stream.Stream;

public enum Difficulty {
    EASY {
        public String toString() {
            return "EASY";
        }
    },
    MEDIUM {
        public String toString() {
            return "MEDIUM";
        }
    },
    HARD {
        public String toString() {
            return "HARD";
        }
    }
}