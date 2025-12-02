/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * SYST 17796 Project
 * @date December 2025
 * @author Dhrumil Rana
 * @author Nisha Rana
 */
package ca.sheridancollege.project;

public class UNOCard extends Card {

    private final UNOColor color;
    private final UNOType type;
    
    private final int value;

    public UNOCard(UNOColor color, UNOType type, int value) {
        this.color = color;
        this.type = type;
        this.value = value;
    }

    public UNOColor getColor() {
        return color;
    }

    public UNOType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public boolean isNumberCard() {
        return type == UNOType.NUMBER;
    }

    public boolean isPlayableOn(UNOCard topCard) {
        if (this.type == UNOType.WILD || this.type == UNOType.WILD_DRAW_FOUR) {
            return true;
        }

        if (topCard.getColor() == UNOColor.NONE) {
            return true;
        }

        if (this.color == topCard.getColor()) {
            return true;
        }

        if (this.isNumberCard() && topCard.isNumberCard() && this.value == topCard.value) {
            return true;
        }

        if (!this.isNumberCard() && this.type == topCard.type) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        if (type == UNOType.NUMBER) {
            return color + " " + value;
        }

        if (type == UNOType.WILD || type == UNOType.WILD_DRAW_FOUR) {
            if (color == UNOColor.NONE) {
                return type.name();
            } else {
                return color + " " + type.name();
            }
        }

        return color + " " + type.name();
    }
}
