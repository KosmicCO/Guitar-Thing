package main;

import engine.Core;
import engine.Input;
import engine.Signal;
import java.util.HashMap;
import java.util.Map;

public class GuitarHero {

    private static Map<Integer, Signal<Boolean>> conv = new HashMap();

    public static void main(String[] args) {

        Core.init();
        Character[] keys = new Character[]{null, null, '1', '2', '3', '4',
        '5', '6', '7', '8', '9', '0', '-', '=', null, null, 'q', 'w',
        'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '[', ']', null, null, 'a', 's',
        'd', 'f', 'g', 'h', 'j', 'k', 'l', ';', '\'', '`', null, '\\', 'z', 'x',
        'c', 'v', 'b', 'n', 'm', ',', '.', '/', null, '*', null, ' ', null, null,
        null, null, null, null, null, null, null, null, null, null, null, '7',
        '8', '9', '-', '4', '5', '6', '+', '1', '2', '3', '0', '.', null, null,
        null, null, null};
        
        String k = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        Map<Character, GuitarString> g = new HashMap();
        int[] cc = init(k, keys);

        for (int i = 0; i < 37; i++) {

            GuitarString s = new GuitarString(440. * Math.pow(2, (i - 24) / 12));
            conv.put(cc[i], s);
        }
        
        Input.keyMap.clear();
        Input.keyMap.putAll(conv);
        
        Core.run();
    }

    public static void keyPressed(Map<Character, GuitarString> mcg, String k) {

        for (char c : k.toCharArray()) {
        }

    }

    public static int[] init(String k, Character[] cc) {

        int[] ia = new int[37];

        for (int i = 0; i < 37; i++) {

            for (int j = 0; j < 50; j++) {

                if (cc[j] != null && cc[j] == k.charAt(i)) {

                    ia[i] = j;
                    break;
                }
            }
        }

        return ia;
    }
}
