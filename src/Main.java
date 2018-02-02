import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String name;
        boolean end = false; // выбрал ли юзер выход из игры
        boolean lost = false; // проверка того, проиграл ли юзер
        int menuChoise; // пункт меню, выбранный юзером

        // начальные силы игрока
        int archers = 50;
        int catapults = 25;
        int swordsmen = 100;

        // начальные силы германских войск
        Random rand = new Random();
        int int10 = 10;
        int int20 = 20;
        int int50 = 50;
        int int70 = 70;
        int int150 = 150;
        int gArchers = int20 + rand.nextInt(int70 - int20 + 1);
        int gCatapults = int10 + rand.nextInt(int50 - int10 + 1);
        int gSwordsmen = int50 + rand.nextInt(int150 - int50 + 1);

        // номера пунктов меню
        int archersMenu, catapultsMenu, swordsmenMenu;
        int fightMenu;

        Scanner in = new Scanner(System.in);
        System.out.println("Добро пожаловать, искатель приключений, как тебя зовут?");
        name = in.next();
        System.out.printf("Что ж, %s, добро пожаловать в игру \"Римский полководец\".", name);
        System.out.println("Тебе предстоит возглавить войска Рима в битве против Германии.");

        while (!end) {
            // число боевых едениц, отправленных в атаку
            int archersSent = 0, catapultsSent = 0, swordsmenSent = 0;
            System.out.printf("В твоём распоряжении %d лучников, %d катапульт, а также %d легионеров.%n", archers, catapults, swordsmen);
            System.out.printf("Германия выставила %d лучников, %d катапульт, а также %d легионеров%n", gArchers, gCatapults, gSwordsmen);

            do {
                // отслеживаем, какие пункты меню были использованы
                int i = 1;
                if (archers > 0 && ((archers - archersSent) != 0)) {
                    archersMenu = i;
                    System.out.printf("[%d] Выдвинуть лучников%n", i);
                    i++;
                } else archersMenu = 0;

                if (catapults > 0 && ((catapults - catapultsSent) != 0)) {
                    catapultsMenu = i;
                    System.out.printf("[%d] Выдвинуть катапульты%n", i);
                    i++;
                } else catapultsMenu = 0;

                if (swordsmen > 0 && ((swordsmen - swordsmenSent) != 0)) {
                    swordsmenMenu = i;
                    System.out.printf("[%d] Выдвинуть легионеров%n", i);
                    i++;
                } else swordsmenMenu = 0;

                fightMenu = i;
                System.out.printf("[%d] В атаку!%n", i);

                menuChoise = in.nextInt();
                if (menuChoise == archersMenu) {
                    do {
                        System.out.println("Сколько лучников отправить в наступление?");
                        archersSent = in.nextInt();
                    } while (!(archersSent > -1) && archersSent <= archers);
                } else if (menuChoise == catapultsMenu) {
                    do {
                        System.out.println("Сколько катапульт отправить в наступление?");
                        catapultsSent = in.nextInt();
                    } while (!(catapultsSent > -1) && catapultsSent <= catapults);
                } else if (menuChoise == swordsmenMenu) {
                    do {
                        System.out.println("Сколько легионеров отправить в наступление?");
                        swordsmenSent = in.nextInt();
                    } while (!(swordsmenSent > -1) && swordsmenSent <= swordsmen);
                }
            } while (menuChoise != fightMenu);

            System.out.println("Сражение началось...");

            int archersDead, catapultsDead, swordsmenDead;
            int gArchersDead, gCatapultsDead, gSwordsmenDead;

            // каждая катапульта убивает 2 лучников
            archersDead = 2 * gCatapults;
            // каждый легионер разрушает 1 катапульту
            catapultsDead = gSwordsmen;
            // каждый лучник убивает 3 легионера
            swordsmenDead = 3 * gArchers;

            gArchersDead = 2 * catapultsSent;
            gCatapultsDead = swordsmenSent;
            gSwordsmenDead = 3 * archersSent;

            // число боевых едениц не должно стать меньше 0
            archers = (archersDead < archers) ? archers - archersDead : 0;
            catapults = (catapultsDead < catapults) ? catapults - catapultsDead : 0;
            swordsmen = (swordsmenDead < swordsmen) ? swordsmen - swordsmenDead : 0;

            gArchers = (gArchersDead < gArchers) ? gArchers - gArchersDead : 0;
            gCatapults = (gCatapultsDead < gCatapults) ? gCatapults - gCatapultsDead : 0;
            gSwordsmen = (gSwordsmenDead < gSwordsmen) ? gSwordsmen - gSwordsmenDead : 0;

            if ((archers + catapults + swordsmen) == 0) end = lost = true;
            else if ((gArchers + gCatapults + gSwordsmen) == 0) {
                end = true;
                lost = false;
            }
        }

        if (lost) {
            System.out.println("Ты потерпел поражение. Попробуй еще раз.");
        } else System.out.println("Поздравляем с победой!");
    }
}
