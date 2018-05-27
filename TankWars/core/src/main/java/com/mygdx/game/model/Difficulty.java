package com.mygdx.game.model;

/**
 * Enum class for difficulty setting with toString methods for each enum
 */
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