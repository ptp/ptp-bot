package me.imsean.ptpbot.commands;

import me.imsean.ptpbot.PTPBot;
import me.imsean.ptpbot.api.command.Command;
import me.imsean.ptpbot.api.mysql.UserManager;
import me.imsean.ptpbot.exceptions.NotAdminException;
import xyz.gghost.jskype.Group;
import xyz.gghost.jskype.message.Message;
import xyz.gghost.jskype.user.GroupUser;
import xyz.gghost.jskype.user.User;

public class BanFromGroupCommand extends Command {

    private final UserManager userManager;

    public BanFromGroupCommand(UserManager userManager) {
        super(GroupUser.Role.MASTER, "ban");
        this.userManager = userManager;
    }

    @Override
    public void onCommand(Message message, Group group, User user, String[] args) {
        if (!this.userManager.isBotAdmin(user)) {
            return;
        }

        if(args.length == 0) {
            group.sendMessage(user.getUsername() + " - Usage: #ban (username)");
        }
        if(args.length > 0) {
            if(args[0].equalsIgnoreCase(PTPBot.getOwner()) || args[0].equalsIgnoreCase(PTPBot.getSkype().getUsername())) {
                group.sendMessage(user.getUsername() + " - r u srs???");
                return;
            }
            try {
                for(GroupUser gu : group.getClients()) {
                    if(gu.getUser().getUsername().equals(args[0])) {
                        if (this.userManager.isBotAdmin(PTPBot.getSkype().getUserByUsername(args[0]))) {
                            group.sendMessage(user.getUsername() + " - r u srs????");
                            return;
                        }
                        this.userManager.ban(group, args[0]);
                        if(!PTPBot.getSkype().getUserByUsername(args[0]).isContact()) {
                            PTPBot.getSkype().getUserByUsername(args[0]).sendContactRequest(PTPBot.getSkype());
                        }
                    }
                }
            } catch (NotAdminException e) {
                group.sendMessage(user.getUsername() + " - I am not ADMIN in this group");
            }
        }
    }

}
