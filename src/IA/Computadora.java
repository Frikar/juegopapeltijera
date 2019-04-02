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

            } else {
                iaselect = "Tijera";
                usuariopuntuaje = usuariopuntuaje + 1;
                resultado = "Ganaste";
                confianza = confianza - 2;
                historiaresultado = 1;
                estrategia();

            }
        }
        aprendizajeIA(2);
        controlIA();
        historiaselect = "Piedra";
    }

    public static void papel() {
        usuarioselect = "Papel";

        random = r.nextInt(102 - 1);

        if (random < chancePapel) {
            iaselect = "Papel";
            resultado = "Empate";
            estrategia();

        } else {

            if (random < (chancePapel + chanceRoca)) {
                iaselect = "Piedra";
                resultado = "Ganaste";
                usuariopuntuaje = usuariopuntuaje + 1;
                confianza = confianza - 2;
                historiaresultado = 1;
                estrategia();

            } else {
                iaselect = "Tijera";
                iapuntuaje = iapuntuaje + 1;
                resultado = "Perdiste";
                confianza = confianza + 1;
                historiaresultado = 0;
                estrategia();

            }
        }

        aprendizajeIA(2);
        controlIA();
        historiaselect = "Papel";
    }

    public static void tijera() {
        usuarioselect = "Tijera";
        random = r.nextInt(102 - 1);

        if (random < chancePapel) {
            iaselect = "Papel";
            resultado = "Ganaste";
            confianza = confianza - 2;
            usuariopuntuaje = usuariopuntuaje + 1;
            historiaresultado = 1;
            estrategia();

        } else {

            if (random < (chancePapel + chanceRoca)) {
                iaselect = "Piedra";
                resultado = "Perdiste";
                confianza = confianza + 1;
                iapuntuaje = iapuntuaje + 1;
                historiaresultado = 0;
                estrategia();

            } else {
                iaselect = "Tijera";
                resultado = "Empate";
                estrategia();

            }
        }
        aprendizajeIA(2/3);
        controlIA();
        historiaselect = "Tijera";
    }

    public static void controlIA() {
        if (alterarWin > repetirWin + memoria) {
            alterarWin -= 2;
        }
        if (repetirWin > alterarWin + memoria) {
            repetirWin -= 2;
        }
        if (alterarEmpate > repetirEmpate + memoria) {
            alterarEmpate -= 2;
        }
        if (repetirEmpate > alterarEmpate + memoria) {
            repetirEmpate -= 2;
        }
        if (alterarPerder > repetirPerder + memoria) {
            alterarPerder -= 2;
        }
        if (repetirPerder > alterarPerder + memoria) {
            repetirPerder -= 2;
        }
        if (chancePapel > 70) {
            chancePapel = 50;
        }
        if (chanceRoca > 70) {
            chanceRoca = 50;
        }
        if (chancePapel < 10) {
            chancePapel = 15;
        }
        if (chanceRoca < 10) {
            chanceRoca = 15;
        }
        if (confianza < 1) {
            confianza = 2;
        }
    }

    public static void aprendizajeIA(int num) {
        if (resultado.equals("Perdiste")) {
            if (alterarPerder > repetirPerder) {
                chanceRoca = chanceRoca - (confianza / num);
                chancePapel = chancePapel - confianza;
            } else {
                if (repetirPerder > alterarPerder) {
                    chanceRoca = chanceRoca + (confianza / num);
                    chancePapel = chanceRoca + confianza;
                }
            }
        } else {

            if (resultado.equals("Ganaste")) {
                if (repetirWin > alterarWin) {
                    chanceRoca = chanceRoca + (confianza / num);
                    chancePapel = chancePapel + confianza;
                } else {
                    if (alterarWin > repetirWin) {
                        chanceRoca = chanceRoca - (confianza / num);
                        chancePapel = chancePapel - confianza;
                    }
                }
            }
        }
    }

    public static void estrategia() {
        if (historiaselect.equals(usuarioselect)) {
            repetirPerder = repetirPerder + 1;
        } else {
            alterarWin = alterarWin + 1;
        }
    }
}
