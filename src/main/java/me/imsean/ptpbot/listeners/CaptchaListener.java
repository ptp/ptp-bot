package me.imsean.ptpbot.listeners;

import xyz.gghost.jskype.event.EventListener;
import xyz.gghost.jskype.events.UserRecaptchaEvent;

import java.util.Scanner;

/**
 * Created by sean on 10/12/15.
 */
public class CaptchaListener implements EventListener {

    public void onCaptcha(UserRecaptchaEvent e) {
        System.out.println(e.getImage());

        Scanner in = new Scanner(System.in);

        System.out.println("Enter captcha:");
        e.setAnswer(in.nextLine());
    }

}
