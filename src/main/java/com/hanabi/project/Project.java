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
                Bukkit.getServer().broadcastMessage( ChatColor.RED + "面の数を入力してください");
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

        if (command.getName().equalsIgnoreCase("mc")) {
            Player player_sender = (Player) sender;
            String name = player_sender.getName();

            if (args.length == 0) {
                Bukkit.getServer().broadcastMessage(ChatColor.RED + "丁の場合は1、半の場合は2を入力してください");
                return false;
            }

            if (args.length == 1) {
                try {
                    int number1 = Integer.parseInt(args[0]);
                    if (number1 == 1) {
                        int chou = (int) Math.ceil(Math.random() * 6) + (int) Math.ceil(Math.random() * 6);
                        if (chou % 2 == 0) {
                            Bukkit.getServer().broadcastMessage("丁が出ました！");
                            sender.sendMessage("当たり！！！");
                            return true;
                        }
                        if (chou % 2 == 1) {
                            Bukkit.getServer().broadcastMessage("半が出ました！");
                            sender.sendMessage("はずれ...");
                            return true;
                        }
                    }
                    if (number1 == 2) {
                        int han = (int) Math.ceil(Math.random() * 6) + (int) Math.ceil(Math.random() * 6);
                        if (han % 2 == 0) {
                            Bukkit.getServer().broadcastMessage("丁が出ました！");
                            sender.sendMessage("はずれ...");
                            return true;
                        }
                        if (han % 2 == 1) {
                            Bukkit.getServer().broadcastMessage("半が出ました！");
                            sender.sendMessage("当たり！！！");
                            return true;
                        }
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getServer().broadcastMessage(ChatColor.RED + "丁か半かを選んでください");
                    return false;

                }

            }
        }


        if (command.getName().equalsIgnoreCase("bank")) {
            Player player_sender = (Player) sender;
            String name = player_sender.getName();

            int money = getConfig().getInt("Player1.count");
            sender.sendMessage("現在の所持金は" + money + "ウォンです");

            return true;
        }

        if (command.getName().equalsIgnoreCase("getmoney")) {
            Player player_sender = (Player) sender;
            String name = player_sender.getName();

            if(args.length == 0){
                Bukkit.getServer().broadcastMessage(ChatColor.RED + "金額を決めてください");
                return  false;
            }

            if(args.length == 1){
                try{
                    int add_money = Integer.parseInt(args[0]);
                    if (add_money >= 1) {
                        int ex_money = getConfig().getInt("Player1.count");
                        int money = add_money + ex_money;
                        sender.sendMessage("銀行に" + add_money + "ウォン追加しました");

                        getConfig().set("Player1.count", money);
                    }  else{
                        Bukkit.getServer().broadcastMessage(ChatColor.RED + "金額は1以上の整数にしてください");
                    }

                } catch (NumberFormatException e) {
                    Bukkit.getServer().broadcastMessage(ChatColor.RED + "1以上の整数を入力してください");
                }

            }
        }
        return false;
    }
}
























