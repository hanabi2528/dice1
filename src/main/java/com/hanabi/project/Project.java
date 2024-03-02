package com.hanabi.project;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Project extends JavaPlugin {

    @Override
    public void onEnable() {    //起動したときの処理
        Bukkit.getLogger().info("プラグインが起動しました");
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("プラグインが終了しました");
        saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) { //入力されたコマンドがdiceかを判定
        if (command.getName().equalsIgnoreCase("dice")) {
            Player player_sender = (Player) sender;
            String name = player_sender.getName();

            if (args.length == 0) {
                Bukkit.getServer().broadcastMessage(ChatColor.RED + "面の数を入力してください");
                return false;
            }

            //argsの文字数をlengthで数える
            if (args.length == 1) {
                try { //string型の変数をint型に変更
                    int maxnumber = Integer.parseInt(args[0]);
                    if (maxnumber > 1000000) {
                        Bukkit.getServer().broadcastMessage(ChatColor.RED + "面の数は1~1000000の整数にしてください");
                        return false;
                    }

                    if (maxnumber >= 1) {
                        //math.randomで0~1の乱数をつくり、面数（maxnumber）でかけて、math.ceilで繰り上げ
                        int number = (int) Math.ceil(Math.random() * maxnumber);
                        Bukkit.getServer().broadcastMessage(name + "は" + maxnumber + "面サイコロを振り" + number + "を出しました");

                        return true;
                    } else {
                        Bukkit.getServer().broadcastMessage(ChatColor.RED + "面の数は1以上の整数にしてください");
                        return false;

                    }
                } catch (NumberFormatException e) {
                    Bukkit.getServer().broadcastMessage(ChatColor.RED + "面の数は1以上の整数にしてください");
                    return false;
                }
            }
        }
        return false;
    }
}



























