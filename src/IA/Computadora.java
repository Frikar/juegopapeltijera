/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import javax.swing.JLabel;
import java.util.Random;

/**
 *
 * @author Diego Vasquez
 */
public class Computadora extends game.gamemenu {

    //Vistas
    public static String usuarioselect, iaselect, resultado, historiaselect = "none";
    public static int iapuntuaje = 0;
    public static int usuariopuntuaje = 0;
    public static int historiaresultado;

    //Funciones para el computador
    public static int random;
    private static double chancePapel = 33;
    private static double chanceRoca = 33;
    private static double repetirWin = 1;
    private static double repetirPerder = 1;
    private static double repetirEmpate = 1;
    private static double alterarWin = 0;
    private static double alterarPerder = 0;
    private static double alterarEmpate = 0;
    private static double conteoPapel = 0;
    private static double conteoRoca = 0;
    private static double conteoTijera = 0;

    //Inteligencia de la computadora
    private static double confianza = 4;
    private static double memoria = 3;
    private static Random r = new Random();

    public static void roca() {
        usuarioselect = "Piedra";

        random = r.nextInt(102 - 1);

        if (random < chancePapel) {
            iaselect = "Papel";
            iapuntuaje = iapuntuaje + 1;
            confianza = confianza + 1;
            resultado = "Perdiste";

            historiaresultado = 0;
            estrategia();
            
        } else {
            
            if (random < (chancePapel + chanceRoca)) {
                iaselect = "Piedra";
                resultado = "Empate";
                estrategia();

            }
            else{
              iaselect = "Tijera";
              usuariopuntuaje = usuariopuntuaje + 1;
              resultado = "Ganaste";
              historiaresultado = 1;
              estrategia();
                
            }
        }
    }

    public static void papel() {
        usuarioselect = "Papel";
    }

    public static void tijera() {
        usuarioselect = "Tijera";
    }

    public static void controlIA() {

    }

    public static void aprendizajeIA() {

    }

    public static void estrategia() {
        if (historiaselect.equals(usuarioselect)) {
            repetirPerder = repetirPerder + 1;
        } else {
            alterarWin = alterarWin + 1;
        }
    }
}
