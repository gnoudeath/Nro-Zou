package nro.main;

import java.io.IOException;
import java.sql.SQLException;

import nro.giftcode.GiftCodeManager;
import nro.io.Message;
import nro.io.Session;
import nro.item.Item;
import nro.item.ItemOption;
import nro.item.ItemSell;
import nro.item.ItemService;
import nro.player.Boss;
import nro.shop.Shop;
import nro.shop.TabItemShop;
import nro.skill.NoiTai;
import nro.skill.NoiTaiTemplate;
import nro.map.Map;
import nro.clan.ClanService;
import nro.clan.Member;
import nro.player.Player;
import nro.player.Detu;
import nro.player.PlayerManger;
import nro.task.TaskService;
import nro.task.TaskManager;
import nro.daihoi.DaiHoiService;
import nro.daihoi.DaiHoiManager;
import nro.player.PlayerDAO;

public class Menu {

    Server server = Server.gI();

    public static void doMenuArray(Player p, int idnpc, String chat, String[] menu) {
        Message m = null;
        try {
            m = new Message(32);
            m.writer().writeShort(idnpc);
            m.writer().writeUTF(chat);
            m.writer().writeByte(menu.length);
            for (byte i = 0; i < menu.length; ++i) {
                m.writer().writeUTF(menu[i]);
            }
            m.writer().flush();
            p.session.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public static void doMenuArraySay(Player p, short id, String[] menu) {
        Message m = null;
        try {
            m = new Message(38);
            m.writer().writeShort(id);
            for (byte i = 0; i < menu.length; i++) {
                m.writer().writeUTF(menu[i]);
            }
            m.writer().flush();
            p.session.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public static void sendWrite(Player p, String title, short type) {
        Message m = null;
        try {
            m = new Message(88);
            m.writer().writeUTF(title);
            m.writer().writeShort(type);
            m.writer().flush();
            p.session.sendMessage(m);
            m.cleanup();
        } catch (IOException var5) {
            var5.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public void textBoxId(Session session, short menuId, String str) {
        Message msg;
        try {
            msg = new Message(88);
            msg.writer().writeInt(menuId);
            msg.writer().writeUTF(str);
            session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendTB(Session session, Player title, String s) {
        Message m = null;
        try {
            m = new Message(94);
            m.writer().writeUTF(title.name);
            m.writer().writeUTF(s);
            m.writer().flush();
            PlayerManger.gI().SendMessageServer(m);
            session.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }

    }

    public void ChatTG(Player p, int avatar, String chat3, byte cmd) {
        Message m = null;
        try {
            m = new Message(-70);
            m.writer().writeShort(avatar);
            m.writer().writeUTF(chat3);
            m.writer().writeByte(cmd);
            m.writer().flush();
            PlayerManger.gI().SendMessageServer(m);
            p.session.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void ChatTG(Player p, short avatar, String str) {
        Message m = null;
        try {
            m = new Message(94);
            m.writer().writeShort(avatar);
            m.writer().writeUTF(str);
            m.writer().flush();
            p.session.sendMessage(m);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void LuckyRound(Player p, byte type, byte soluong) throws IOException {
        Message m = null;
        try {
            if (type == 0) {
                m = new Message(-127);
                m.writer().writeByte(type);
                short[] arId = new short[]{2280, 2281, 2282, 2283, 2284, 2285, 2286};
                m.writer().writeByte(7);
                for (short i = 0; i < arId.length; i++) {
                    m.writer().writeShort(arId[i]);
                }
                m.writer().writeByte(soluong);
                m.writer().writeInt(10000);
                m.writer().writeShort(0);
                m.writer().flush();
                p.session.sendMessage(m);
            } else if (type == 1) {
                m = new Message(-127);
                m.writer().writeByte(soluong);
                short[] arId = new short[]{2, 3, 4, 5, 6, 7, 8};
                for (short i = 0; i < soluong; i++) {
                    m.writer().writeShort(arId[i]);
                }
                m.writer().flush();
                p.session.sendMessage(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void confirmMenu(Player p, Message m) throws IOException, SQLException, InterruptedException {
        short idNpc = m.reader().readShort();
        byte select = m.reader().readByte();
//        Util.log("ID NPC: " + idNpc);
//        Util.log("SELECT: " + select);
//        Util.log("p.menuID: " + p.menuID);
        switch (p.menuNPCID) {
            case 999: {
                if (p.id == 1 && p.name.equals("admin")) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            doMenuArray(p, idNpc, "LIST BOSS", new String[]{"Broly", "Super Broly", "Cooler", "Cooler 2", "Black",
                                "Super Black", "Kuku", "Mập Đinh", "Rambo", "Số 4", "Số 3", "Số 2", "Số 1", "Tiểu Đội\nTrưởng", "Fide 1", "Fide 2", "Fide 3",
                                "Android 19", "Dr.Kôrê", "Android 15", "Android 14", "Android 13", "Poc", "Pic", "KingKong", "Xên 1", "Xên 2", "Xên Hoàn\nThiện",
                                "Xên Con", "Siêu Bọ\nHung", "Trung úy\nTrắng", "Trung úy\nXanh Lơ", "Trung úy\nThép", "Ninja\nÁo Tím", "Robốt\nVệ Sĩ",
                                "Drabura", "Pui Pui", "Ya Côn", "Ma Bư", "Chilled", "Chilled 2", "Dr Lychee", "Hatchiyack", "Tập sự", "Tân binh", "Chiến binh", "Đội trưởng", "Zamasu", "Bill", "Whis", "Super Zou"});
                        } else if (select == 1) {
                            m = null;
                            try {
                                m = new Message(-125);
                                m.writer().writeUTF("Buff Item To Player");
                                m.writer().writeByte((byte) 3);
                                m.writer().writeUTF("Name Player");
                                m.writer().writeByte((byte) 1);
                                m.writer().writeUTF("ID Item");
                                m.writer().writeByte((byte) 0);
                                m.writer().writeUTF("Số lượng");
                                m.writer().writeByte((byte) 0);
                                m.writer().flush();
                                p.session.sendMessage(m);
                                m.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (m != null) {
                                    m.cleanup();
                                }
                            }
                        } else if (select == 2) {
                            GiftCodeManager.gI().checkInfomationGiftCode(p);
                        }
                        p.menuID = select;
                        break;
                    } else if (p.menuID == 0) {
                        byte typeBoss = (byte) (select + 1);
                        Boss _bossCall = new Boss(Server.gI().idBossCall, typeBoss, p.x, p.y);
                        if (typeBoss == (byte) 2) {
                            Detu _rDetu = new Detu();
                            _rDetu.initDetuBroly(_rDetu);
                            _rDetu.id = -300000 - Server.gI().idBossCall;
                            _rDetu.x = (short) (_bossCall.x - 100);
                            _rDetu.y = (short) (_bossCall.y - 100);
                            _bossCall.detu = _rDetu;
                            p.zone.pets.add(_rDetu);
                            for (Player _pz : p.zone.players) {
                                p.zone.loadInfoDeTu(_pz.session, _rDetu);
                            }
                        }
                        Server.gI().idBossCall++;

                        p.zone.bossMap.add(_bossCall);

                        if (typeBoss == (byte) 1 || typeBoss == (byte) 2) {
                            p.zone.loadBROLY(_bossCall);
                        } else {
                            p.zone.loadBossNoPet(_bossCall);
                        }
                        break;
                    }
                }
                break;
            }
            case 100: { //NOITAI
                if (p.menuID == -1) {
                    if (select == 0) {
                        try {
                            m = new Message(112);
                            m.writer().writeByte(1);
                            if (p.gender == 1) {
                                m.writer().writeByte(1);
                            } else {
                                m.writer().writeByte(1);
                            }
                            m.writer().writeUTF("Nội tại");
                            if (p.gender == 0) {
                                m.writer().writeByte(10);
                                for (byte i = 0; i < 10; i++) {
                                    m.writer().writeShort(NoiTaiTemplate.listNoiTaiTD.get((byte) (i + 1)).idIcon);
                                    m.writer().writeUTF(NoiTaiTemplate.listNoiTaiTD.get((byte) (i + 1)).infoTemp);
                                }
                            } else if (p.gender == 1) {
                                m.writer().writeByte(11);
                                for (byte i = 0; i < 11; i++) {
                                    m.writer().writeShort(NoiTaiTemplate.listNoiTaiNM.get((byte) (i + 1)).idIcon);
                                    m.writer().writeUTF(NoiTaiTemplate.listNoiTaiNM.get((byte) (i + 1)).infoTemp);
                                }
                            } else {
                                m.writer().writeByte(10);
                                for (byte i = 0; i < 10; i++) {
                                    m.writer().writeShort(NoiTaiTemplate.listNoiTaiXD.get((byte) (i + 1)).idIcon);
                                    m.writer().writeUTF(NoiTaiTemplate.listNoiTaiXD.get((byte) (i + 1)).infoTemp);
                                }
                            }
                            m.writer().flush();
                            p.session.sendMessage(m);
                            m.cleanup();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (m != null) {
                                m.cleanup();
                            }
                        }
                    }
                    if (select == 1) {
                        doMenuArray(p, idNpc, "Bạn có muốn mở nội tại với giá " + vangMoNoiTai(p.countMoNoiTai) + "tr vàng?", new String[]{"Mở\nNội tại", "Từ chối"});
                    }
                    if (select == 2) {
                        doMenuArray(p, idNpc, "Bạn có muốn mở nội tại với giá 100 ngọc?", new String[]{"Mở\nNội tại", "Từ chối"});
                    }
                    if (select == 3) {
                        break;
                    }
                    p.menuID = select;
                    break;
                } else if (p.menuID == 1) { //MO NOI TAI THUONG
                    if (select == 0) {
                        int _goldOPEN = vangMoNoiTai(p.countMoNoiTai) * 1000000;
                        if (p.vang >= _goldOPEN && p.canOpenNoiTai) {
                            p.canOpenNoiTai = false;
                            p.vang -= _goldOPEN;
                            p.countMoNoiTai = (byte) (p.countMoNoiTai + 1) > (byte) 8 ? (byte) 8 : (byte) (p.countMoNoiTai + 1);
                            Service.gI().buyDone(p);
                            int _indexOPEN = 1;
                            //RANDOM INDEX NOI TAI
                            if (p.gender == 1) {
                                _indexOPEN = Util.nextInt(1, 12); //NAMEK CO 11 NOI TAI
                                //                        p.noiTai = p.noiTai.newNoiTai(NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN));
                                p.noiTai = new NoiTai(NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).id, NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).param);
                                p.noiTai.idIcon = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).idIcon;
                                p.noiTai.min = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).min;
                                p.noiTai.max = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).max;
                                p.noiTai.idSkill = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).idSkill;
                                p.noiTai.infoTemp = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).infoTemp;
                                p.noiTai.infoHead = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).infoHead;
                                p.noiTai.infoFoot = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).infoFoot;

                                p.noiTai.param = (short) (Util.nextInt(p.noiTai.min, (p.noiTai.max + 1)));
                            } else if (p.gender == 0) {
                                _indexOPEN = Util.nextInt(1, 11); //TD CO 10 NOI TAI
                                p.noiTai = new NoiTai(NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).id, NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).param);
                                p.noiTai.idIcon = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).idIcon;
                                p.noiTai.min = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).min;
                                p.noiTai.max = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).max;
                                p.noiTai.idSkill = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).idSkill;
                                p.noiTai.infoTemp = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).infoTemp;
                                p.noiTai.infoHead = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).infoHead;
                                p.noiTai.infoFoot = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).infoFoot;
                                p.noiTai.param = (short) (Util.nextInt(p.noiTai.min, (p.noiTai.max + 1)));
                            } else if (p.gender == 2) {
                                _indexOPEN = Util.nextInt(1, 11); //TD CO 10 NOI TAI
                                p.noiTai = new NoiTai(NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).id, NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).param);
                                p.noiTai.idIcon = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).idIcon;
                                p.noiTai.min = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).min;
                                p.noiTai.max = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).max;
                                p.noiTai.idSkill = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).idSkill;
                                p.noiTai.infoTemp = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).infoTemp;
                                p.noiTai.infoHead = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).infoHead;
                                p.noiTai.infoFoot = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).infoFoot;
                                p.noiTai.param = (short) (Util.nextInt(p.noiTai.min, (p.noiTai.max + 1)));
                            }
                            Controller.getInstance().sendNoiTaiHienTai(p.session, p);
                            p.canOpenNoiTai = true;
                            break;
                        } else {
                            p.sendAddchatYellow("Không đủ vàng để mở nội tại!");
                        }
                        break;
                    } else if (select == 1) {
                        break;
                    }
                } else if (p.menuID == 2) { //MO NOI TAI VIP
                    if (select == 0) {
                        if (p.ngoc >= 100 && p.canOpenNoiTai) {
                            p.ngoc -= 100;
                            p.countMoNoiTai = (byte) 1;
                            Service.gI().buyDone(p);
                            int _indexOPEN = 1;
                            //RANDOM INDEX NOI TAI
                            if (p.gender == 1) {
                                _indexOPEN = Util.nextInt(1, 12); //NAMEK CO 11 NOI TAI
                                //                        p.noiTai = p.noiTai.newNoiTai(NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN));
                                p.noiTai = new NoiTai(NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).id, NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).param);
                                p.noiTai.idIcon = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).idIcon;
                                p.noiTai.min = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).min;
                                p.noiTai.max = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).max;
                                p.noiTai.idSkill = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).idSkill;
                                p.noiTai.infoTemp = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).infoTemp;
                                p.noiTai.infoHead = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).infoHead;
                                p.noiTai.infoFoot = NoiTaiTemplate.listNoiTaiNM.get(_indexOPEN).infoFoot;

                                p.noiTai.param = (short) (Util.nextInt(p.noiTai.min, (p.noiTai.max + 1)));
                            } else if (p.gender == 0) {
                                _indexOPEN = Util.nextInt(1, 11); //TD CO 10 NOI TAI
                                p.noiTai = new NoiTai(NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).id, NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).param);
                                p.noiTai.idIcon = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).idIcon;
                                p.noiTai.min = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).min;
                                p.noiTai.max = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).max;
                                p.noiTai.idSkill = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).idSkill;
                                p.noiTai.infoTemp = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).infoTemp;
                                p.noiTai.infoHead = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).infoHead;
                                p.noiTai.infoFoot = NoiTaiTemplate.listNoiTaiTD.get(_indexOPEN).infoFoot;
                                p.noiTai.param = (short) (Util.nextInt(p.noiTai.min, (p.noiTai.max + 1)));
                            } else if (p.gender == 2) {
                                _indexOPEN = Util.nextInt(1, 11); //TD CO 10 NOI TAI
                                p.noiTai = new NoiTai(NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).id, NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).param);
                                p.noiTai.idIcon = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).idIcon;
                                p.noiTai.min = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).min;
                                p.noiTai.max = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).max;
                                p.noiTai.idSkill = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).idSkill;
                                p.noiTai.infoTemp = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).infoTemp;
                                p.noiTai.infoHead = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).infoHead;
                                p.noiTai.infoFoot = NoiTaiTemplate.listNoiTaiXD.get(_indexOPEN).infoFoot;
                                p.noiTai.param = (short) (Util.nextInt(p.noiTai.min, (p.noiTai.max + 1)));
                            }
                            Controller.getInstance().sendNoiTaiHienTai(p.session, p);
                            p.canOpenNoiTai = true;
                            break;
                        } else {
                            p.sendAddchatYellow("Không đủ ngọc để mở nội tại!");
                        }
                    } else if (select == 1) {
                        break;
                    }
                }
                break;
            }
            case 99: {//RADA DO NGOC RONG NAMEC
                if (p.menuID == -1) {
                    if (p.imgNRSD == (byte) 53 && p.nrNamec != 0) {
                        p.sendAddchatYellow("Đang đeo ngọc rồng không thể di chuyển!");
                    } else {
                        if (select == 0) {
                            if (p.ngoc >= 10) {
                                p.ngoc -= 10;
                                Service.gI().buyDone(p);
                                Service.gI().teleportToNrNamec(p);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc để di chuyển!");
                            }
                        } else if (select == 1) {
                            if (p.vang >= 100000) {
                                p.vang -= 100000;
                                Service.gI().buyDone(p);
                                Service.gI().teleportToNrNamec(p);
                            } else {
                                p.sendAddchatYellow("Không đủ vàng để di chuyển!");
                            }
                        }
                    }
                    break;
                }
                break;
            }
            case 52: {
                if (p.map.id == 0) {
                    if (p.menuID == -1) {
                        if (select == 0) {

                            if (p.truItemBySL(851, 99)) {
                                //   p.pointnew +=20;
                                //    HelperDAO.PointEventTORI(p, 20 ); // cộng điểm ở đây
                                    

                                Item _itemBuffRandom = ItemSell.getItemNotSell(1170);
                                int xsl = 1;
                                _itemBuffRandom.quantity = xsl;
                                p.addItemToBag(_itemBuffRandom);
                                p.sendAddchatYellow("Nhân được " + xsl + " " + _itemBuffRandom.template.name);
                                Service.gI().updateItemBag(p);
                                PlayerDAO.updateDB(p);
                            } else {
                                p.sendAddchatYellow("Tìm 99 sà cân rồi quay lại đây");
                            }
                        }
                        if (select == 1) {
                            if (p.truItemBySL(949, 99)) {
                                //   p.pointnew +=20;
                                //  HelperDAO.PointEventTORI(p, 20 ); // cộng điểm ở đây
                                    
                                Item _itemBuffRandom = ItemSell.getItemNotSell(1167);
                                int xql = 1;
                                _itemBuffRandom.quantity = xql;
                                p.addItemToBag(_itemBuffRandom);
                                p.sendAddchatYellow("Nhân được " + xql + " " + _itemBuffRandom.template.name);
                                Service.gI().updateItemBag(p);
                                PlayerDAO.updateDB(p);
                            } else {
                                p.sendAddchatYellow("Tìm 99 Thịt rồi quay lại đây");
                            }

                        }
                        if (select == 2) {
                            if (p.truItemBySL(1167, 1)) {
                                if (p.truItemBySL(1170, 1)) {
                                    // p.pointnew +=20;
                                    // HelperDAO.PointEventTORI(p, 40 ); // cộng điểm ở đây
                                    
                                    Item _itemBuffRandom = ItemSell.getItemNotSell(1169);
                                    int uam = 1;
                                    _itemBuffRandom.quantity = uam;
                                    p.addItemToBag(_itemBuffRandom);
                                    p.sendAddchatYellow("Nhân được " + uam + " " + _itemBuffRandom.template.name);
                                    Service.gI().updateItemBag(p);
                                    PlayerDAO.updateDB(p);
                                } else {
                                    p.sendAddchatYellow("khum đủ 2 hộp quà");
                                }

                            }
                        }
                            // if (select == 3) {
                            //     Service.chatNPC(p, (short) 24, HelperDAO.getTopsukien());
                            //     break;
                            // }
                            // break;
                        
                    }
                    break;
                }
            }
            case 73: {
                //            if(p.map.id == 5) {
                if (p.menuID == -1) {
                    if (select == 0) {
                        Service.gI().clientInput(p, "Nhập password mới", "Password", (byte) 0);
                        break;
                    } else if (select == 1) {
                        break;
                    }
                }
                //            }
                break;
            }
            case 72: {
                if (p.map.id == 160) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            TabItemShop[] test = Shop.getTabShop(72, 0).toArray(new TabItemShop[0]);
                            GameScr.UIshop(p, test);
                        } else if (select == 1) {
                            break;
                        }
                    }
                }
                break;
            }
            case 66: { // noi banh
                if (p.map.id == 5) {
                    if (p.menuID == -1) {
                        if (select == 0) {

                            if (p.truItemBySL(753, 1)) {
                                p.diemsukien += 20;
                                int listvp[] = {381, 382, 383, 384, 386, 15, 16, 17, 457};
                                int rdIDITEM = Util.nextInt(0, 9);

                                Item _itemBuffRandom = ItemSell.getItemNotSell(listvp[rdIDITEM]);
                                int xsl = Util.nextInt(1, 10);
                                _itemBuffRandom.quantity += xsl;
                                p.addItemToBag(_itemBuffRandom);
                                p.sendAddchatYellow("Nhân được " + xsl + " " + _itemBuffRandom.template.name);
                                Service.gI().updateItemBag(p);
                                PlayerDAO.updateDB(p);
                            } else {
                                p.sendAddchatYellow("Tìm bánh trưng rồi quay lại đây");
                            }
                        }
                        if (select == 1) {
                            if (!p.truItemBySL(1129, 23) && !p.truItemBySL(1130, 23)
                                    && !p.truItemBySL(1131, 23) && !p.truItemBySL(1132, 23)) {
                                p.sendAddchatYellow("Thiếu chữ");
                                return;
                            }
                            p.diemsukien += 20;
                            int listvp[] = {14, 15, 16, 17, 381, 382, 383, 384, 385, 386, 457, 1110, 1111, 1114, 897, 457, 828, 829, 830, 831, 832, 833, 834, 835, 836, 837, 838, 839, 840, 841, 842, 859, 956};
                            //int slvp = Util.nextInt(1, );
                            int rdvp = Util.nextInt(0, 33);
                            Item lixitet = ItemSell.getItemNotSell(listvp[rdvp]);
                            //int soluong = Util.nextInt(1, 10);
                            //lixitet.quantity += soluong;
                            p.addItemToBag(lixitet);
                            Service.gI().updateItemBag(p);
                            PlayerDAO.updateDB(p);
                            p.sendAddchatYellow("Nhận được " + lixitet.template.name);

                        }
                        if (select == 2) {
                            Service.chatNPC(p, (short) 24, HelperDAO.getTopPoint());
                            break;
                        }
                        break;
                    }
                }
                break;

            }
            case 67: { //MR POPO
                if (p.map.id == 0) {
                    if (p.menuID == -1) {
                        if (select == 3) {
                            if (p.clan != null) {
                                if (p.clan.openKhiGas) {
                                    doMenuArray(p, idNpc, "Bang hội của cậu đang tham gia Khí Gas, cậu có muốn tham gia?", new String[]{"OK", "Từ chối"});
                                } else {
                                    Service.gI().clientInput(p, "Hãy chọn cấp độ từ 1-110", "Cấp độ", (byte) 0);
                                }
                            } else {
                                p.sendAddchatYellow("Cậu chưa có bang hội nên không thể tham gia");
                            }
                        }
                        p.menuID = select;
                        break;
                    }
                    if (p.menuID == 3) {
                        if (select == 0) {
                            if (p.clan != null) {
                                if (p.clan.openKhiGas && p.clan.khiGas[0] != null) {
                                    Service.gI().leaveOutMap(p);
                                    p.x = (short) 63;
                                    p.y = (short) 336;
                                    p.clan.khiGas[0].area[0].Enter(p);
                                } else {
                                    p.sendAddchatYellow("Khí Gas hiện tại chưa mở!");
                                }
                            }
                        }
                        break;
                    }
                }
                break;
            }
            case 61: { //GOKU DOI YARDRAT
                if (p.map.id == 133) {
                    if (p.menuID == -1 && select == 0) {
                        if (p.truItemBySL(590, 999)) {
                            Item yardrat = ItemSell.getItemNotSell(((int) p.gender + 592));
                            Item _yardrat = new Item(yardrat);
                            p.addItemToBag(_yardrat);
                            Service.gI().updateItemBag(p);
                            p.sendAddchatYellow("Bạn vừa nhận được võ phục Yardrat");
                        } else {
                            p.sendAddchatYellow("Cần 999 Bí kiếp để đổi võ phục Yardrat");
                        }
                        //                    p.menuID = select;
                        break;
                    }
                }
                break;
            }
            case 60: { //GOKU NUI KHI VANG
                if (p.taskId >= (short) 24) {
                    if (p.map.id == 80) {
                        if (p.menuID == -1) {
                            if (select == 0) {
                                p.zone.goMapTransport(p, 131);
                            }
                            p.menuID = select;
                            break;
                        }
                    } else if (p.map.id == 131) {
                        if (p.menuID == -1) {
                            if (select == 0) {
                                p.zone.goMapTransport(p, 80);
                            }
                            p.menuID = select;
                            break;
                        }
                    }
                } else {
                    p.sendAddchatYellow("Không thể thực hiện");
                }
                break;
            }
            case 56: { //WHIS TODO
                if (p.map.id == 154) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            if (p.check9999Manhthiensu()) { //OPEN CUA HANG THIEN Su
                                doMenuArray(p, idNpc, "Ta sẽ giúp ngươi chế tạo trang bị Thiên Sứ", new String[]{"OK", "Đóng"});
                                break;
                            } else {
                                doMenuArray(p,idNpc,"Còn không mau kiếm đủ 9999 mảnh trang bị",new String[]{"OK"});
                            }
                        }
                        p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && select == 0) {
                        p.menuID = 1;
                        Message msg = null;
                        try {
                            msg = new Message(-81);
                            msg.writer().writeByte(0);
                            msg.writer().writeUTF("Cần 1 công thức\nMảnh trang bị tương ứng\n1 đá nâng cấp (tùy chọn)\n1 đá may mắn (tùy chọn)\nTheo đúng thứ tự (công thức, mảnh trang bị, đá nâng cấp, đá may mắn)");
                            msg.writer().writeUTF("Chế tạo\ntrang bị thiên sứ");
                            msg.writer().writeShort((short) 56);
                            msg.writer().flush();
                            p.session.sendMessage(msg);
                            msg.cleanup();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (msg != null) {
                                msg.cleanup();
                            }
                        }
                    }
                    if (p.menuID == 1 && select == 0) { // xac nhan che tao do thien su
                        ItemService.gI().confirmCreateItemAngel(p);
                        break;
                    }
                }
                break;
            }
            case 55: { //THAN HUY DIET BILL
                if (p.map.id == 48) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            if (p.checkFullSetThan()) { //OPEN CUA HANG DO HUY DIET
                                TabItemShop[] test = Shop.getTabShop(55, 0).toArray(new TabItemShop[0]);
                                GameScr.UIshop(p, test);
                                break;
                            } else {
                                doMenuArray(p, idNpc, "Đã Mặc đủ set thần đéo đâu?", new String[]{"OK"});
                            }
                        }
                        p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && select == 0) {
                        break;
                    }
                } else if (p.map.id == 154) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            p.zone.goMapTransport(p, 50);
                        }
                        if (select == 1) {
                            break;
                        }
                    }
                }
                break;
            }
            case 54: {
                if (p.map.id == 5) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            TabItemShop[] test = Shop.getTabShop(54, 0).toArray(new TabItemShop[0]);
                            GameScr.UIshop(p, test);
                        } if (select == 1) {
                            TabItemShop[] test = Shop.getTabShop(54, 1).toArray(new TabItemShop[0]);
                            GameScr.UIshop(p, test);
                        }
                        if(select ==2){
                              Service.chatNPC(p, (short) 24, HelperDAO.getTopBXHLTN());
                        }
                    }
                }
                break;
            }
            case 53:{ //TAPION
                if(p.map.id == 19) {
                    if(p.menuID == -1) {
                        if(select == 0){
                            if(Server.gI().openHiru) {
                                int keyHiru = p.getIndexItemBagByID(722);
                                if(keyHiru != -1 && p.ItemBag[keyHiru].quantity >= 5) {
                                    p.ItemBag[keyHiru].quantity -= 5;
                                    if(p.ItemBag[keyHiru].quantity <= 0) {
                                        p.ItemBag[keyHiru] = null;
                                    }
                                    Service.gI().updateItemBag(p);
                                    GotoMap(p, 126);
                                } else {
                                    p.sendAddchatYellow("Cần 5 Capsule hồng để tiến vào đây");
                                }
                            } else {
                                p.sendAddchatYellow("Hirudegarn chỉ mở vào khung giờ 22h đến 23h hàng ngày");
                            }
                        } else if(select == 1){
                            break;
                        }
                    }
                } else if(p.map.id == 126) {
                    if(p.menuID == -1) {
                        if(select == 0){
                            GotoMap(p, 19);
                        } else if(select == 1){
                            break;
                        }
                    }
                }
                break;
            }
            case 74:{
                if(p.map.id == 5) {
                    if(p.menuID == -1)
                    {
                        if(select == 0){
                            TabItemShop[] test = Shop.getTabShop(74, 0).toArray(new TabItemShop[0]);
                            GameScr.UIshop(p, test);
                        } else if(select == 1) {
                            break;
                        }
                    }
                }
                break;
            }
            case 49: { //DUONG TANG
                if (p.map.id == 0) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            GotoMap(p, 123);
                        }
                    }
                } else if (p.map.id == 123) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            GotoMap(p, 0);
                        }
                    }
                } else if (p.map.id == 122) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            break;
                        } else if (select == 1) {
                            GotoMap(p, 0);
                        }
                    }
                }
                break;
            }
            case 47: { //NGUU MA VUONG
                if (p.map.id == 153) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            GotoMap(p, 156);
                        } else if (select == 1) {
                            break;
                        }
                    }
                }
                break;
            }
            case 46: { //BABIDAY
                if (p.map.id == 114 || p.map.id == 115 || p.map.id == 117 || p.map.id == 118 || p.map.id == 119 || p.map.id == 120) {
                    if (p.menuID == -1) {
                        if (select == 1) {
                            if (p.ngoc >= 1) {
                                p.ngoc -= 1;
                                Service.gI().buyDone(p);
                                p.socolaMabu = (byte) 0;
                                Service.gI().loadPoint(p.session, p);
                                Service.gI().loadCaiTrangTemp(p);
                                if (p.cPk == (byte) 11) {
                                    p.cPk = (byte) 10;
                                    p.detu.cPk = (byte) 10;
                                    Service.gI().changeFlagPK(p, (byte) 10);
                                    if (p.petfucus == 1) {
                                        Service.gI().changeFlagPKPet(p, (byte) 10);
                                    }
                                }
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc");
                            }
                        } else if (select == 2) {
                            if (p.pointMabu >= (byte) 10) {
                                if (p.map.id == 115) {
                                    GotoMap(p, 117);
                                } else if (p.map.id == 120) {
                                    GotoMap(p, 52);
                                } else {
                                    GotoMap(p, (p.map.id + 1));
                                }
                            } else {
                                p.sendAddchatYellow("Chưa đủ thể lực để xuống tầng tiếp theo");
                            }
                        }
                    }
                }
                break;
            }
            case 44:{ //OSIN
                if(p.map.id == 50) {
                    if(p.menuID == -1) {
                        if(select == 0){
                            p.zone.goMapTransport(p, 48);
                        } else if(select == 1){
                            if(p.power >= 40000000000L) {
                                p.zone.goMapTransport(p, 154);
                            } else {
                                p.sendAddchatYellow("Ngươi cần đạt 40 tỷ sức mạnh mới có thể tới đây");
                            }
                        } else {
                            break;
                        }
                    }
                } else if(p.map.id == 154){
                    if(p.menuID == -1) {
                        if(select == 0){
                            break;
                        } else if(select == 0){
                            if(p.power >= 80000000000L) {
                                p.zone.goMapTransport(p, 155);
                            } else {
                                p.sendAddchatYellow("Ngươi cần đạt 80 tỷ sức mạnh mới có thể tới đây");
                            }
                        } else {
                            break;
                        }
                    }
                } else if(p.map.id == 155){
                    if(p.menuID == -1) {
                        if(select == 0){
                            if(p.power >= 80000000000L) {
                                p.zone.goMapTransport(p, 154);
                            } else {
                                p.sendAddchatYellow("Ngươi cần đạt 80 tỷ sức mạnh mới có thể tới đây");
                            }
                        } else {
                            break;
                        }
                    }
                } else if(p.map.id == 52) {
                    if(p.menuID == -1) {
                        if(select == 0){
                            if(Server.gI().openMabu) {
                                GotoMap(p, 114);
    //                            p.zone.goMapTransport(p, 114);
                            } else {
                                p.sendAddchatYellow("12h hàng ngày, Ôsin sẽ dẫn bạn đuổi theo 2 tên đồ tể");
                            }
                        } else {
                            break;
                        }
                    }
                } else if(p.map.id == 114 || p.map.id == 115 || p.map.id == 117 || p.map.id == 118 || p.map.id == 119 || p.map.id == 120) {
                    if(p.menuID == -1) {
                        if(select == 1){
                            if(p.ngoc >= 1) {
                                p.ngoc -= 1;
                                Service.gI().buyDone(p);
                                p.socolaMabu = (byte)0;
                                Service.gI().loadPoint(p.session, p);
                                Service.gI().loadCaiTrangTemp(p);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc");
                            }
                        } else if(select == 2){
                            if(p.pointMabu >= (byte)10) {
                                if(p.map.id == 115) {
                                    GotoMap(p, 117);
                                } else if(p.map.id == 120) {
                                    GotoMap(p, 52);
                                } else {
                                    GotoMap(p, (p.map.id + 1));
                                }
                            } else {
                                p.sendAddchatYellow("Chưa đủ thể lực để xuống tầng tiếp theo");
                            }
                        }
                    }
                }
                break;
            }
            case 43: { //TO SU MO GIOI HAN SUC MANH
                if (p.map.id == 50) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            doMenuArray(p, idNpc, "Ê cu làm tí đồ cho khỏe lên kh?", new String[]{"Giòn nha\n1 tỉ vàng", "Từ chối"});
                        }
                        if (select == 1) {
                            doMenuArray(p, idNpc, "Mua đồ cho thằng em m k?", new String[]{"Có chứ Có chứ\n1 tỉ vàng", "Từ chối"});
                        }
                        if (select == 2) {
                            break;
                        }
                        p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0) { //NANG GIOI HAN SUC MANH CHO SU PHU
                        if (select == 0) {
                            if (p.limitPower >= 9) {
                                doMenuArray(p, idNpc, "DCMM max rồi nâng nâng cái l", new String[]{});
                            } else {
                                if (p.power >= 80000000000L) {
                                    if (p.limitPower < 10 && p.vang >= 1000000000) {
                                        p.vang -= 1000000000;
                                        p.limitPower += 1;
                                        Service.gI().buyDone(p);
                                        p.sendAddchatYellow("Ấy Lực hơn rồi đấy");
                                    }
                                } else {
                                    doMenuArray(p, idNpc, "Đã max đ đâu mà mở?", new String[]{});
                                }
                            }
                        }
                        if (select == 1) {
                            break;
                        }
                    }
                    if (p.menuID == 1) { //NANG GIOI HAN SUC MANH CHO DE TU
                        if (select == 0) {
                            if (p.detu != null && p.havePet == (byte) 1) {
                                if (p.detu.limitPower >= 9) {
                                    doMenuArray(p, idNpc, "Đệ tử mày max rồi mở cc nữa à", new String[]{});
                                } else {
                                    if (p.power >= 80000000000L) {
                                        if (p.detu.limitPower < 10 && p.vang >= 1000000000) {
                                            p.vang -= 1000000000;
                                            p.detu.limitPower += 1;
                                            Service.gI().buyDone(p);
                                            p.sendAddchatYellow("Thằng em m khỏe hơn r đó");
                                        }
                                    } else {
                                        doMenuArray(p, idNpc, "Cu cậu chưa đủ 80 tỏi kìa con", new String[]{});
                                    }
                                }
                            }
                        }
                        if (select == 1) {
                            break;
                        }
                    }
                }
                break;
            }
            case 42: { //QUOC VUONG MO GIOI HAN SUC MANH
                if (p.map.id == 43) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            doMenuArray(p, idNpc, "Nâng giới hạn sức mạnh sẽ khiến con trở nên mạnh mẽ hơn", new String[]{"Nâng ngay\n1 tỉ vàng", "Từ chối"});
                        }
                        if (select == 1) {
                            doMenuArray(p, idNpc, "Nâng giới hạn sức mạnh sẽ khiến đệ tử của con trở nên mạnh mẽ hơn", new String[]{"Nâng ngay\n1 tỉ vàng", "Từ chối"});
                        }
                        if (select == 2) {
                            break;
                        }
                        p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0) { //NANG GIOI HAN SUC MANH CHO SU PHU
                        if (select == 0) {
                            if (p.limitPower >= 7) {
                                doMenuArray(p, idNpc, "DCMM max rồi nâng nâng cái l", new String[]{});
                            } else {
                                if (p.power >= 18000000000L) {
                                    if (p.limitPower < 8 && p.vang >= 1000000000) {
                                        p.vang -= 1000000000;
                                        p.limitPower += 1;
                                        Service.gI().buyDone(p);
                                        p.sendAddchatYellow("Mở giới hạn sức mạnh cho bản thân thành công");
                                    }
                                } else {
                                    doMenuArray(p, idNpc, "Đã max đ đâu mà mở?", new String[]{});
                                }
                            }
                        }
                        if (select == 1) {
                            break;
                        }
                    }
                    if (p.menuID == 1) { //NANG GIOI HAN SUC MANH CHO DE TU
                        if (select == 0) {
                            if (p.detu != null && p.havePet == (byte) 1) {
                                if (p.detu.limitPower >= 7) {
                                    doMenuArray(p, idNpc, "Đệ tử mày max rồi mở cc nữa à", new String[]{});
                                } else {
                                    if (p.power >= 18000000000L) {
                                        if (p.detu.limitPower < 8 && p.vang >= 1000000000) {
                                            p.vang -= 1000000000;
                                            p.detu.limitPower += 1;
                                            Service.gI().buyDone(p);
                                            p.sendAddchatYellow("Mở giới hạn sức mạnh cho đệ tử thành công");
                                        }
                                    } else {
                                        doMenuArray(p, idNpc, "Con chưa đủ mạnh để nâng giới hạn sức mạnh cho đệ tử!", new String[]{});
                                    }
                                }
                            }
                        }
                        if (select == 1) {
                            break;
                        }
                    }
                }
                break;
            }
            case 41: { //TRUNG THU
                if (p.map.id == 14) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            TabItemShop[] test = Shop.getTabShop(41, 0).toArray(new TabItemShop[0]);
                            GameScr.UIshop(p, test);
                        }
                    }
                }
                break;
            }
            case 39: {
                if (p.map.id == 5) {
                    if (select == 0) {
                        TabItemShop[] test = Shop.getTabShop(39, (int) (p.gender)).toArray(new TabItemShop[0]);
                        GameScr.UIshop(p, test);
                        break;
                    }
                }
                break;
            }
            case 38: {
                if (p.map.id == 27 || p.map.id == 102) {
                    if (p.taskId >= (short) 23) {
                        if (select == 0) {
                            if (p.map.id != 102) {
                                p.waitTransport = true;
                                Service.gI().transportTauNgam(p, (short) 30, (byte) 0);
                                Service.gI().teleportByTauNgam(p, 102, (long) 31000);
                                //                    GotoMap(p,102);
                            } else {
                                GotoMap(p, 24 + p.gender);
                            }
                        }
                        if (select == 1) {
                            break;
                        }
                    } else {
                        p.sendAddchatYellow("Phải hoàn thành nhiệm vụ trước khi tới đây");
                    }
                }
                break;
            }
            case 37: {
                if (p.map.id == 102) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            break;
                        } else if (select == 1) {
                            if (p.taskId >= (short) 24) {
                                TabItemShop[] test = Shop.getTabShop(37, 0).toArray(new TabItemShop[0]);
                                GameScr.UIshop(p, test);
                            }
                        }
                    }
                }
                break;
            }
            case 36: { //NGOC RONG SAO DEN
                if (p.map.id == 85) {
                    if (p.menuID == -1) {
                        if (select == 0) { //phu ho
                            if (p.imgNRSD == (byte) 37) {
                                doMenuArray(p, idNpc, "Ta sẽ giúp ngươi tăng HP và KI lên mức kinh hoàng, ngươi hãy chọn đi", new String[]{"x3 HP\n50 ngọc", "x5 HP\n100 ngọc", "x7 HP\n150 ngọc", "Từ chối"});
                                p.menuID = select;
                            } else {
                                p.sendAddchatYellow("Không thể thực hiện");
                            }
                            break;
                        }
                        if (select == 1) {
                            break;
                        }
                        //                p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && p.imgNRSD == (byte) 37) {
                        if (select == 0) {
                            if (p.ngoc >= 50) {
                                p.ngoc -= 50;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 3;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        } else if (select == 1) {
                            if (p.ngoc >= 100) {
                                p.ngoc -= 100;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 5;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        } else if (select == 2) {
                            if (p.ngoc >= 150) {
                                p.ngoc -= 150;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 7;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        }
                    }
                }
                break;
            }
            case 35: { //NGOC RONG SAO DEN
                if (p.map.id == 91) {
                    if (p.menuID == -1) {
                        if (select == 0) { //phu ho
                            if (p.imgNRSD == (byte) 37) {
                                doMenuArray(p, idNpc, "Ta sẽ giúp ngươi tăng HP và KI lên mức kinh hoàng, ngươi hãy chọn đi", new String[]{"x3 HP\n50 ngọc", "x5 HP\n100 ngọc", "x7 HP\n150 ngọc", "Từ chối"});
                                p.menuID = select;
                            } else {
                                p.sendAddchatYellow("Không thể thực hiện");
                            }
                            break;
                        }
                        if (select == 1) {
                            break;
                        }
                        //                p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && p.imgNRSD == (byte) 37) {
                        if (select == 0) {
                            if (p.ngoc >= 50) {
                                p.ngoc -= 50;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 3;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        } else if (select == 1) {
                            if (p.ngoc >= 100) {
                                p.ngoc -= 100;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 5;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        } else if (select == 2) {
                            if (p.ngoc >= 150) {
                                p.ngoc -= 150;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 7;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        }
                    }
                }
                break;
            }
            case 34: { //NGOC RONG SAO DEN
                if (p.map.id == 90) {
                    if (p.menuID == -1) {
                        if (select == 0) { //phu ho
                            if (p.imgNRSD == (byte) 37) {
                                doMenuArray(p, idNpc, "Ta sẽ giúp ngươi tăng HP và KI lên mức kinh hoàng, ngươi hãy chọn đi", new String[]{"x3 HP\n50 ngọc", "x5 HP\n100 ngọc", "x7 HP\n150 ngọc", "Từ chối"});
                                p.menuID = select;
                            } else {
                                p.sendAddchatYellow("Không thể thực hiện");
                            }
                            break;
                        }
                        if (select == 1) {
                            break;
                        }
                        //                p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && p.imgNRSD == (byte) 37) {
                        if (select == 0) {
                            if (p.ngoc >= 50) {
                                p.ngoc -= 50;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 3;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        } else if (select == 1) {
                            if (p.ngoc >= 100) {
                                p.ngoc -= 100;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 5;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        } else if (select == 2) {
                            if (p.ngoc >= 150) {
                                p.ngoc -= 150;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 7;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        }
                    }
                }
                break;
            }
            case 33: { //NGOC RONG SAO DEN
                if (p.map.id == 89) {
                    if (p.menuID == -1) {
                        if (select == 0) { //phu ho
                            if (p.imgNRSD == (byte) 37) {
                                doMenuArray(p, idNpc, "Ta sẽ giúp ngươi tăng HP và KI lên mức kinh hoàng, ngươi hãy chọn đi", new String[]{"x3 HP\n50 ngọc", "x5 HP\n100 ngọc", "x7 HP\n150 ngọc", "Từ chối"});
                                p.menuID = select;
                            } else {
                                p.sendAddchatYellow("Không thể thực hiện");
                            }
                            break;
                        }
                        if (select == 1) {
                            break;
                        }
                        //                p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && p.imgNRSD == (byte) 37) {
                        if (select == 0) {
                            if (p.ngoc >= 50) {
                                p.ngoc -= 50;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 3;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        } else if (select == 1) {
                            if (p.ngoc >= 100) {
                                p.ngoc -= 100;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 5;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        } else if (select == 2) {
                            if (p.ngoc >= 150) {
                                p.ngoc -= 150;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 7;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        }
                    }
                }
                break;
            }
            case 32: { //NGOC RONG SAO DEN
                if (p.map.id == 88) {
                    if (p.menuID == -1) {
                        if (select == 0) { //phu ho
                            if (p.imgNRSD == (byte) 37) {
                                doMenuArray(p, idNpc, "Ta sẽ giúp ngươi tăng HP và KI lên mức kinh hoàng, ngươi hãy chọn đi", new String[]{"x3 HP\n50 ngọc", "x5 HP\n100 ngọc", "x7 HP\n150 ngọc", "Từ chối"});
                                p.menuID = select;
                            } else {
                                p.sendAddchatYellow("Không thể thực hiện");
                            }
                            break;
                        }
                        if (select == 1) {
                            break;
                        }
                        //                p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && p.imgNRSD == (byte) 37) {
                        if (select == 0) {
                            if (p.ngoc >= 50) {
                                p.ngoc -= 50;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 3;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        } else if (select == 1) {
                            if (p.ngoc >= 100) {
                                p.ngoc -= 100;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 5;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        } else if (select == 2) {
                            if (p.ngoc >= 150) {
                                p.ngoc -= 150;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 7;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        }
                    }
                }
                break;
            }
            case 31: { //NGOC RONG SAO DEN
                if (p.map.id == 87) {
                    if (p.menuID == -1) {
                        if (select == 0) { //phu ho
                            if (p.imgNRSD == (byte) 37) {
                                doMenuArray(p, idNpc, "Ta sẽ giúp ngươi tăng HP và KI lên mức kinh hoàng, ngươi hãy chọn đi", new String[]{"x3 HP\n50 ngọc", "x5 HP\n100 ngọc", "x7 HP\n150 ngọc", "Từ chối"});
                                p.menuID = select;
                            } else {
                                p.sendAddchatYellow("Không thể thực hiện");
                            }
                            break;
                        }
                        if (select == 1) {
                            break;
                        }
                        //                p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && p.imgNRSD == (byte) 37) {
                        if (select == 0) {
                            if (p.ngoc >= 50) {
                                p.ngoc -= 50;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 3;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        } else if (select == 1) {
                            if (p.ngoc >= 100) {
                                p.ngoc -= 100;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 5;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        } else if (select == 2) {
                            if (p.ngoc >= 150) {
                                p.ngoc -= 150;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 7;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        }
                    }
                }
                break;
            }
            case 30: { //NGOC RONG SAO DEN
                if (p.map.id == 86) {
                    if (p.menuID == -1) {
                        if (select == 0) { //phu ho
                            if (p.imgNRSD == (byte) 37) {
                                doMenuArray(p, idNpc, "Ta sẽ giúp ngươi tăng HP và KI lên mức kinh hoàng, ngươi hãy chọn đi", new String[]{"x3 HP\n50 ngọc", "x5 HP\n100 ngọc", "x7 HP\n150 ngọc", "Từ chối"});
                                p.menuID = select;
                            } else {
                                p.sendAddchatYellow("Không thể thực hiện");
                            }
                            break;
                        }
                        if (select == 1) {
                            break;
                        }
                        //                p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && p.imgNRSD == (byte) 37) {
                        if (select == 0) {
                            if (p.ngoc >= 50) {
                                p.ngoc -= 50;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 3;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        } else if (select == 1) {
                            if (p.ngoc >= 100) {
                                p.ngoc -= 100;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 5;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        } else if (select == 2) {
                            if (p.ngoc >= 150) {
                                p.ngoc -= 150;
                                Service.gI().buyDone(p);
                                p.xHPSaoDen = (byte) 7;
                                p.hp = p.getHpFull();
                                Service.gI().loadPoint(p.session, p);
                                p.updateHpToPlayerInMap(p, p.hp);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc!");
                            }
                        }
                    }
                }
                break;
            }
            case 29: { //NGOC RONG SAO DEN
                if (p.map.id == 24 || p.map.id == 25 || p.map.id == 26) {
                    if (p.menuID == -1) {
                        if (p.indexNRSD.size() == 0) {
                            if (select == 0) {
                                Service.chatNPC(p, (short) p.menuNPCID, "Mỗi ngày từ 20h đến 21h các hành tinh có Ngọc Rồng Sao Đen sẽ xảy ra 1 cuộc đại chiến. Người nào tìm thấy và giữ được Ngọc Rồng Sao Đen sẽ mang phần thưởng về cho bang của mình trong vòng 1 ngày\nCác phần thưởng như sau\n1 sao đen: +20% sức đánh cho toàn bang\n2 sao đen: +30% HP tối đa cho toàn bang\n3 sao đen: +30% KI tối đa cho toàn bang\n4 sao đen: +30% PST cho toàn bang\n5 sao đen: +20% Hút HP và KI cho toàn bang\n6 sao đen: +30% Hút TNSM cho toàn bang\n7 sao đen: +10% Chí Mạng cho toàn bang");
                            }
                            if (select == 1) {
                                if (Server.gI().openNRSD) {
                                    Service.gI().openUISaoDen(p);
                                } else {
                                    p.sendAddchatYellow("Ngọc rồng sao đen chỉ mở vào khung giờ 20h đến 21h hàng ngày");
                                }
                            }
                            if (select == 2) {
                                break;
                            }
                            p.menuID = select;
                            break;
                        } else {
                            if (select == 0) {
                                Service.chatNPC(p, (short) p.menuNPCID, "Mỗi ngày từ 20h đến 21h các hành tinh có Ngọc Rồng Sao Đen sẽ xảy ra 1 cuộc đại chiến. Người nào tìm thấy và giữ được Ngọc Rồng Sao Đen sẽ mang phần thưởng về cho bang của mình trong vòng 1 ngày\nCác phần thưởng như sau\n1 sao đen: +20% sức đánh cho toàn bang\n2 sao đen: +30% HP tối đa cho toàn bang\n3 sao đen: +30% KI tối đa cho toàn bang\n4 sao đen: +30% PST cho toàn bang\n5 sao đen: +20% Hút HP và KI cho toàn bang\n6 sao đen: +30% TNSM cho toàn bang\n7 sao đen: +10% Chí Mạng cho toàn bang");
                            }
                            if (select == 1) {
                                String[] stringNRSD = new String[p.indexNRSD.size()];
                                for (byte i = 0; i < p.indexNRSD.size(); i++) {
                                    if (((System.currentTimeMillis() - p.timeNRSD[p.indexNRSD.get(i)]) >= (long) 3600000) || p.indexNRSD.get(i) <= (byte) 1) {
                                        stringNRSD[i] = "Nhận\nthưởng\n" + (byte) (p.indexNRSD.get(i) + 1) + " sao";
                                    } else {
                                        stringNRSD[i] = (byte) (p.indexNRSD.get(i) + 1) + " sao\n" + (int) (60 - (int) ((System.currentTimeMillis() - p.timeNRSD[p.indexNRSD.get(i)]) / 60000)) + " phút";
                                    }
                                }
                                doMenuArray(p, idNpc, "Ngươi đang có phần thưởng ngọc sao đen, có muốn nhận không?", stringNRSD);
                            }
                            if (select == 2) {
                                if (Server.gI().openNRSD) {
                                    Service.gI().openUISaoDen(p);
                                } else {
                                    p.sendAddchatYellow("Ngọc rồng sao đen chỉ mở vào khung giờ 20h đến 21h hàng ngày");
                                }
                            }
                            if (select == 3) {
                                break;
                            }
                            p.menuID = select;
                            break;
                        }
                    }
                    if (p.indexNRSD.size() == 0) {

                    } else {
                        if (p.menuID == 1) {
                            if (p.indexNRSD.get((byte) select) > (byte) 6) {
                                byte indexNRD = p.indexNRSD.get((byte) select);
                                if ((int) ((System.currentTimeMillis() - p.timeNRSD[indexNRD]) / 60000) > 60) {
                                    if (indexNRD == (byte) 2) {
                                        p.sendAddchatYellow("Phần thưởng này đã có tác dụng");
                                    } else if (indexNRD == (byte) 3) {
                                        p.sendAddchatYellow("Phần thưởng này đã có tác dụng");
                                    } else if (indexNRD == (byte) 4) {
                                        p.sendAddchatYellow("Phần thưởng này đã có tác dụng");
                                    } else if (indexNRD == (byte) 5) {
                                        p.sendAddchatYellow("Phần thưởng này đã có tác dụng");
                                    } else if (indexNRD == (byte) 6) {
                                        p.sendAddchatYellow("Phần thưởng này đã có tác dụng");
                                    }
                                    p.timeNRSD[indexNRD] = System.currentTimeMillis();
                                } else {
                                    p.sendAddchatYellow("Chưa thể nhận, hãy chờ hết thời gian");
                                }
                            } else {
                                p.sendAddchatYellow("Phần thưởng này đã có tác dụng");
                            }
                            break;
                        }
                    }
                }
                break;
            }
            case 27: { //RONG THAN NAMEC
                if (p.menuID == -1) {
                    if (select == 0) {
                        if (p.clan != null) {
                            Player memClan = null;
                            Item _itemDBV = null;
                            Item itemDBV = ItemSell.getItemNotSell(987);
                            for (Member _mem : p.clan.members) {
                                memClan = PlayerManger.gI().getPlayerByUserID(_mem.id);
                                if (memClan != null && memClan.session != null) { //CHECK MEMBER ONLINE MOI DUOC NHAN
                                    _itemDBV = new Item(itemDBV);
                                    _itemDBV.quantity = 10;
                                    memClan.addItemToBag(_itemDBV);
                                    Service.gI().updateItemBag(memClan);
                                    memClan.sendAddchatYellow("Điều ước của ngươi đã trở thành sự thật!");
                                }
                            }
                        }
                    }
                    Service.gI().endEffCallDragon(p);
                    break;
                }
                break;
            }
            case 25: {
                if (p.map.id == 27) {
                    if (select == 0) {
                        if (p.clan == null) {
                            p.sendAddchatYellow("Ngươi chưa có bang hội!");
                        } else {
                            if (p.clan.members.size() < 5) {
                                p.sendAddchatYellow("Bang hội phải có ít nhất 5 thành viên mới có thể tiến hành đi doanh trại Độc Nhãn");
                            } else {
                                if ((System.currentTimeMillis() - p.clan.tcreate) >= (long) 10800000) {
                                    if ((System.currentTimeMillis() - p.clan.topen) >= (long) 10800000 || p.clan.openDoanhTrai) {
                                        boolean chkOpenDT = false;
                                        for (int i = 0; i < p.zone.players.size(); i++) {
                                            if (p.zone.players.get(i).clan != null && p.zone.players.get(i).id != p.id) {
                                                if (p.zone.players.get(i).clan.id == p.clan.id) {
                                                    chkOpenDT = true;
                                                    break;
                                                }
                                            }
                                        }
                                        if (chkOpenDT || p.clan.openDoanhTrai) {
                                            if (Server.gI().cDoanhTrai < Server.gI().maxDoanhTrai) {
                                                GotoMap(p, 53);
                                            } else {
                                                p.sendAddchatYellow("Hiện tại doanh trại Độc Nhãn đang quá tải, vui lòng chờ 30 phút nữa");
                                            }
                                        } else {
                                            p.sendAddchatYellow("Cần ít nhất 2 thành viên để mở doanh trại Độc Nhãn");
                                        }
                                    } else {
                                        p.sendAddchatYellow("Hôm qua các ngươi đã đi doanh trại rồi, hãy chờ đến lượt tiếp theo");
                                    }
                                } else {
                                    p.sendAddchatYellow("Bang hội phải tạo được 3 giờ trở lên mới có thể tiến hành doanh trại Độc Nhãn");
                                }
                            }
                        }
                    }
                    if (select == 1) {
                        break;
                    }
                }
                break;
            }
            case 24: { //RONG THAN TRAI DAT
                if (p.menuID == -1) {
                    if (select == 0) { //DEP TRAI NHAT VU TRU
                        ItemSell avatarVIP = ItemSell.getItemSell(((int) p.gender + 227), (byte) 1);
                        Item _AVATARVIP = new Item(avatarVIP.item);
                        _AVATARVIP.itemOptions.clear();
                        _AVATARVIP.itemOptions.add(new ItemOption(50, Util.nextInt(15, 51)));
                        _AVATARVIP.itemOptions.add(new ItemOption(77, Util.nextInt(15, 51)));
                        _AVATARVIP.itemOptions.add(new ItemOption(103, Util.nextInt(15, 51)));
                        p.addItemToBag(_AVATARVIP);
                        Service.gI().updateItemBag(p);
                    } else if (select == 1) { // GANG TAY DANG MANG LEN 1 CAP
                        if (p.ItemBody[2] != null && p.ItemBody[2].getParamItemByID(72) < 7) {
                            if (p.ItemBody[2].getParamItemByID(72) == 0) {
                                ItemOption itemOptionNew = new ItemOption(72, 1);
                                p.ItemBody[2].itemOptions.add(itemOptionNew);
                                for (byte i = 0; i < p.ItemBody[2].itemOptions.size(); i++) {
                                    if (p.ItemBody[2].itemOptions.get(i).id == 0) {
                                        p.ItemBody[2].itemOptions.get(i).param = (int) Math.ceil(p.ItemBody[2].itemOptions.get(i).param * 1.1);
                                    }
                                }
                            } else {
                                for (byte i = 0; i < p.ItemBody[2].itemOptions.size(); i++) {
                                    if (p.ItemBody[2].itemOptions.get(i).id == 72) {
                                        p.ItemBody[2].itemOptions.get(i).param += 1;
                                    }
                                    if (p.ItemBody[2].itemOptions.get(i).id == 0) {
                                        p.ItemBody[2].itemOptions.get(i).param = (int) Math.ceil(p.ItemBody[2].itemOptions.get(i).param * 1.1);
                                    }
                                }
                            }
                        }
                        Service.gI().updateItemBody(p);
                        Service.gI().loadPoint(p.session, p);
                        p.LOADCAITRANGTOME();
                    } else if (select == 2) { // DOI SKILL 2, 3 DE TU
                        if (p.havePet == 1 && (p.detu.power >= 150000000L)) {
                            int rdSkill2 = Util.nextInt(0, 4);
                            if (rdSkill2 == 0) {
                                p.detu.listSkill.get(1).skillId = (short) 7; //id kame lv1
                                p.detu.listSkill.get(1).point = 1;
                                p.detu.listSkill.get(1).genderSkill = (byte) 0;
                                p.detu.listSkill.get(1).tempSkillId = (short) 1;
                            } else if (rdSkill2 == 1) {
                                p.detu.listSkill.get(1).skillId = (short) 21; //id masenko lv1
                                p.detu.listSkill.get(1).point = 1;
                                p.detu.listSkill.get(1).genderSkill = (byte) 1;
                                p.detu.listSkill.get(1).tempSkillId = (short) 3;
                            } else if (rdSkill2 == 2) {
                                p.detu.listSkill.get(1).skillId = (short) 35; //id atomic lv1
                                p.detu.listSkill.get(1).point = 1;
                                p.detu.listSkill.get(1).genderSkill = (byte) 2;
                                p.detu.listSkill.get(1).tempSkillId = (short) 5;
                            } else {
                                p.detu.listSkill.get(1).skillId = (short) 21; //id masenko lv1
                                p.detu.listSkill.get(1).point = 1;
                                p.detu.listSkill.get(1).genderSkill = (byte) 1;
                                p.detu.listSkill.get(1).tempSkillId = (short) 3;
                            }
                            //RANDOM SKILL 3
                            rdSkill2 = Util.nextInt(0, 4);
                            if (rdSkill2 == 0) {
                                p.detu.listSkill.get(2).skillId = (short) 42; //id tdhs lv1
                                p.detu.listSkill.get(2).point = 1;
                                p.detu.listSkill.get(2).genderSkill = (byte) 0;
                                p.detu.listSkill.get(2).tempSkillId = (short) 6;
                            } else if (rdSkill2 == 1) {
                                p.detu.listSkill.get(2).skillId = (short) 63; //id kaioken lv1
                                p.detu.listSkill.get(2).point = 1;
                                p.detu.listSkill.get(2).genderSkill = (byte) 0;
                                p.detu.listSkill.get(2).tempSkillId = (short) 9;
                            } else if (rdSkill2 == 2) {
                                p.detu.listSkill.get(2).skillId = (short) 56; //id ttnl lv1
                                p.detu.listSkill.get(2).point = 1;
                                p.detu.listSkill.get(2).genderSkill = (byte) 2;
                                p.detu.listSkill.get(2).tempSkillId = (short) 8;
                            } else {
                                p.detu.listSkill.get(2).skillId = (short) 63; //id kaioken lv1
                                p.detu.listSkill.get(2).point = 1;
                                p.detu.listSkill.get(2).genderSkill = (byte) 0;
                                p.detu.listSkill.get(2).tempSkillId = (short) 9;
                            }
                        }
                    } else if (select == 3) {
                        //RANDOM SKILL 3
                        if (p.havePet == 1 && (p.detu.power >= 20000000000L)) {
                            int rdSkill2 = Util.nextInt(0, 4);
                            if (rdSkill2 == 0) {
                                p.detu.listSkill.get(2).skillId = (short) 42; //id tdhs lv1
                                p.detu.listSkill.get(2).point = 1;
                                p.detu.listSkill.get(2).genderSkill = (byte) 0;
                                p.detu.listSkill.get(2).tempSkillId = (short) 6;
                            } else if (rdSkill2 == 1) {
                                p.detu.listSkill.get(2).skillId = (short) 63; //id kaioken lv1
                                p.detu.listSkill.get(2).point = 1;
                                p.detu.listSkill.get(2).genderSkill = (byte) 0;
                                p.detu.listSkill.get(2).tempSkillId = (short) 9;
                            } else if (rdSkill2 == 2) {
                                p.detu.listSkill.get(2).skillId = (short) 56; //id ttnl lv1
                                p.detu.listSkill.get(2).point = 1;
                                p.detu.listSkill.get(2).genderSkill = (byte) 2;
                                p.detu.listSkill.get(2).tempSkillId = (short) 8;
                            } else {
                                p.detu.listSkill.get(2).skillId = (short) 63; //id kaioken lv1
                                p.detu.listSkill.get(2).point = 1;
                                p.detu.listSkill.get(2).genderSkill = (byte) 0;
                                p.detu.listSkill.get(2).tempSkillId = (short) 9;
                            }
                            //RANDOM SKILL 4
                            rdSkill2 = Util.nextInt(0, 3);
                            if (rdSkill2 == 0) {
                                p.detu.listSkill.get(3).skillId = (short) 91; //id bien khi lv1
                                p.detu.listSkill.get(3).point = 1;
                                p.detu.listSkill.get(3).genderSkill = (byte) 2;
                                p.detu.listSkill.get(3).tempSkillId = (short) 13;
                            } else if (rdSkill2 == 1) {
                                p.detu.listSkill.get(3).skillId = (short) 77; //id khien nang luong lv1
                                p.detu.listSkill.get(3).point = 1;
                                p.detu.listSkill.get(3).genderSkill = (byte) 1;
                                p.detu.listSkill.get(3).tempSkillId = (short) 12;
                            } else {
                                p.detu.listSkill.get(3).skillId = (short) 121; //id khien nang luong lv1
                                p.detu.listSkill.get(3).point = 1;
                                p.detu.listSkill.get(3).genderSkill = (byte) 0;
                                p.detu.listSkill.get(3).tempSkillId = (short) 19;
                            }
                        }
                    } else if (select == 4) { //GANG TAY DE TU DANG MANG LEN 1 CAP
                        if (p.detu.ItemBody[2] != null && p.detu.ItemBody[2].getParamItemByID(72) < 7) {
                            if (p.detu.ItemBody[2].getParamItemByID(72) == 0) {
                                ItemOption itemOptionNew = new ItemOption(72, 1);
                                p.detu.ItemBody[2].itemOptions.add(itemOptionNew);
                                for (byte i = 0; i < p.detu.ItemBody[2].itemOptions.size(); i++) {
                                    if (p.detu.ItemBody[2].itemOptions.get(i).id == 0) {
                                        p.detu.ItemBody[2].itemOptions.get(i).param = (int) Math.ceil(p.detu.ItemBody[2].itemOptions.get(i).param * 1.1);
                                    }
                                }
                            } else {
                                for (byte i = 0; i < p.detu.ItemBody[2].itemOptions.size(); i++) {
                                    if (p.detu.ItemBody[2].itemOptions.get(i).id == 72) {
                                        p.detu.ItemBody[2].itemOptions.get(i).param += 1;
                                    }
                                    if (p.detu.ItemBody[2].itemOptions.get(i).id == 0) {
                                        p.detu.ItemBody[2].itemOptions.get(i).param = (int) Math.ceil(p.detu.ItemBody[2].itemOptions.get(i).param * 1.1);
                                    }
                                }
                            }
                        }
                        Service.gI().updateItemBody(p.detu);
                        //                    p.LOADCAITRANGTOME();
                    } else if (select == 5) { // QUAN DANG MANG LEN 1 CAP
                        if (p.ItemBody[1] != null && p.ItemBody[1].getParamItemByID(72) < 7) {
                            if (p.ItemBody[1].getParamItemByID(72) == 0) {
                                ItemOption itemOptionNew = new ItemOption(72, 1);
                                p.ItemBody[1].itemOptions.add(itemOptionNew);
                                for (byte i = 0; i < p.ItemBody[1].itemOptions.size(); i++) {
                                    if (p.ItemBody[2].itemOptions.get(i).id == 0) {
                                        p.ItemBody[2].itemOptions.get(i).param = (int) Math.ceil(p.ItemBody[1].itemOptions.get(i).param * 1.1);
                                    }
                                }
                            } else {
                                for (byte i = 0; i < p.ItemBody[1].itemOptions.size(); i++) {
                                    if (p.ItemBody[1].itemOptions.get(i).id == 72) {
                                        p.ItemBody[1].itemOptions.get(i).param += 1;
                                    }
                                    if (p.ItemBody[1].itemOptions.get(i).id == 0) {
                                        p.ItemBody[1].itemOptions.get(i).param = (int) Math.ceil(p.ItemBody[1].itemOptions.get(i).param * 1.1);
                                    }
                                }
                            }
                        }
                        Service.gI().updateItemBody(p);
                        Service.gI().loadPoint(p.session, p);
                        p.LOADCAITRANGTOME();
                    } else if (select == 6) { // GIAY DANG MANG LEN 1 CAP
                        if (p.ItemBody[3] != null && p.ItemBody[3].getParamItemByID(72) < 7) {
                            if (p.ItemBody[3].getParamItemByID(72) == 0) {
                                ItemOption itemOptionNew = new ItemOption(72, 1);
                                p.ItemBody[3].itemOptions.add(itemOptionNew);
                                for (byte i = 0; i < p.ItemBody[3].itemOptions.size(); i++) {
                                    if (p.ItemBody[3].itemOptions.get(i).id == 0) {
                                        p.ItemBody[3].itemOptions.get(i).param = (int) Math.ceil(p.ItemBody[3].itemOptions.get(i).param * 1.1);
                                    }
                                }
                            } else {
                                for (byte i = 0; i < p.ItemBody[1].itemOptions.size(); i++) {
                                    if (p.ItemBody[3].itemOptions.get(i).id == 72) {
                                        p.ItemBody[3].itemOptions.get(i).param += 1;
                                    }
                                    if (p.ItemBody[3].itemOptions.get(i).id == 0) {
                                        p.ItemBody[3].itemOptions.get(i).param = (int) Math.ceil(p.ItemBody[3].itemOptions.get(i).param * 1.1);
                                    }
                                }
                            }
                        }
                        Service.gI().updateItemBody(p);
                        Service.gI().loadPoint(p.session, p);
                        p.LOADCAITRANGTOME();
                    } else if (select == 7) { // MOT CO NGUOI YEU LUA DAO
                        //                    p.sendAddchatYellow("Bay màu nhé!");
                        p.tiemNang += (long) 200000000;
                        p.power += (long) 200000000;
                        p.UpdateSMTN((byte) 2, (long) 200000000);
                    } else if (select == 8) {
                        p.critNr = (byte) (p.critNr + 2) > (byte) 10 ? (byte) 10 : (byte) (p.critNr + 2);
                        Service.gI().loadPoint(p.session, p);
                    }
                    p.sendAddchatYellow("Điều ước của người đã trở thành sự thật!");
                    Service.gI().endEffCallDragon(p);
                    //                Message msg = null;
                    //                try {
                    //                    msg = new Message(-83);
                    //                    msg.writer().writeByte(1);
                    //                    msg.writer().flush();
                    //                    for(Player _p: p.zone.players) {
                    //                        _p.session.sendMessage(msg);
                    //                    }
                    //                    msg.cleanup();
                    //                } catch (Exception e) {
                    //                    e.printStackTrace();
                    //                } finally {
                    //                    if(msg != null) {
                    //                        msg.cleanup();
                    //                    }
                    //                }
                    break;
                }
                break;
            }
            // ba hat mit
            case 21: {
                if (p.map.id == 5) {
                    if (p.menuID == -1) {
                        Message msg = null;
                        if (select == 0) { //ep sao trang bi
                            try {
                                msg = new Message(-81);
                                msg.writer().writeByte(0);
                                msg.writer().writeUTF("Vào hành trang\nChọn trang bị\n(Áo, quần, găng, giày hoặc rada) có ô đặt\nsao pha lê\nChọn loại sao pha lê\nSau đó chọn 'Nâng cấp'");
                                msg.writer().writeUTF("Ta sẽ phù phép\ncho trang bị của ngươi\ntrở nên mạnh mẽ");
                                msg.writer().flush();
                                p.session.sendMessage(msg);
                                msg.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (msg != null) {
                                    msg.cleanup();
                                }
                            }
                        }
                        if (select == 1) { // pha lee hoa trang bi
                            try {
                                msg = new Message(-81);
                                msg.writer().writeByte(0);
                                msg.writer().writeUTF("Vào hành trang\nChọn trang bị\n(Áo, quần, găng, giày hoặc rada)\n Sau đó chọn 'Nâng cấp'");
                                msg.writer().writeUTF("Ta sẽ phù phép\ncho trang bị của ngươi\ntrở thành trang bị pha lê");
                                msg.writer().flush();
                                p.session.sendMessage(msg);
                                msg.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (msg != null) {
                                    msg.cleanup();
                                }
                            }
                        }
                        if (select == 4) { // pha lee hoa trang bi
                            try {
                                msg = new Message(-81);
                                msg.writer().writeByte(0);
                                msg.writer().writeUTF("Bỏ 1 món đồ thần linh bất kì vào");
                                msg.writer().writeUTF("Ta sẽ phù phép biến nó thành item có giá trị");
                                msg.writer().flush();
                                p.session.sendMessage(msg);
                                msg.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (msg != null) {
                                    msg.cleanup();
                                }
                            }
                        }
                        p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && select == 0) { //xac nhan nang cap
                        Service.gI().sendEpStarItem(p);
                        break;
                    }
                    if (p.menuID == 1 && select == 0) { //xac nhan nang cap
                        Service.gI().sendUpStarItem(p);
                        break;
                    }
                    if (p.menuID == 4 && select == 0) { //xac nhan nang cap
                        ItemService.gI().sendUpSItemExchange(p);
                        break;
                    }
                    break;
                } else if (p.map.id == 42 || p.map.id == 43 || p.map.id == 44) {
                    if (p.menuID == -1) {
                        Message msg = null;
                        if (select == 0) { //mua buaf
                            doMenuArray(p, idNpc, "Bùa của ta rất lợi hại. Mua xong có tác dụng ngay nhé, nhớ tranh thủ sử dụng, thoát game phí lắm. Mua càng nhiều thời gian giá càng rẻ!", new String[]{"Bùa Dùng 1\ngiờ", "Bùa Dùng 8\ngiờ", "Bùa Dùng 1\ntháng"});
                        }
                        if (select == 1) { //nang cap trang bi
                            try {
                                msg = new Message(-81);
                                msg.writer().writeByte(0);
                                msg.writer().writeUTF("Vào hành trang\nChọn trang bị\n(Áo, quần, găng, giày hoặc rada)\nChọn loại đá để nâng cấp\nĐá bảo vệ đặt ở vị trí cuối(nếu có)\nSau đó chọn 'Nâng cấp'");
                                msg.writer().writeUTF("Ta sẽ phù phép\ncho trang bị của ngươi\ntrở nên mạnh mẽ");
                                msg.writer().flush();
                                p.session.sendMessage(msg);
                                msg.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (msg != null) {
                                    msg.cleanup();
                                }
                            }
                        }
                        if (select == 2) { //lam phep nhap da
                            try {
                                msg = new Message(-81);
                                msg.writer().writeByte(0);
                                msg.writer().writeUTF("Vào hành trang\nChọn 10 mảnh đá vụn\nChọn một bình nước phép\nSau đó chọn 'Làm phép'");
                                msg.writer().writeUTF("Ta sẽ phù phép\ncho 10 mảnh đá vụn\ntrở thành 1 đá nâng cấp");
                                msg.writer().flush();
                                p.session.sendMessage(msg);
                                msg.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (msg != null) {
                                    msg.cleanup();
                                }
                            }
                        }
                        if (select == 3) { //Nhap ngoc rong
                            try {
                                msg = new Message(-81);
                                msg.writer().writeByte(0);
                                msg.writer().writeUTF("Vào hành trang\nChọn 7 viên ngọc cùng sao\nSau đó chọn 'Làm phép'");
                                msg.writer().writeUTF("Ta sẽ phù phép\ncho 7 viên Ngọc Rồng\nthành 1 viên Ngọc Rồng cấp cao");
                                msg.writer().flush();
                                p.session.sendMessage(msg);
                                msg.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (msg != null) {
                                    msg.cleanup();
                                }
                            }
                        } else if (select == 4) { //NANG CAP BONG TAI PORATA
                            try {
                                msg = new Message(-81);
                                msg.writer().writeByte(0);
                                msg.writer().writeUTF("Vào hành trang\nChọn bông tai Porata\nChọn mảnh bông tai để nâng cấp, số lượng\ṇ̣̣9999 cái\nSau đó chọn 'Nâng cấp'");
                                msg.writer().writeUTF("Ta sẽ phù phép\ncho bông tai Porata của ngươi\nthành cấp 2");
                                msg.writer().flush();
                                p.session.sendMessage(msg);
                                msg.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (msg != null) {
                                    msg.cleanup();
                                }
                            }
                        } else if (select == 5) { //MO CHI SO BONG TAI PORATA2
                            try {
                                msg = new Message(-81);
                                msg.writer().writeByte(0);
                                msg.writer().writeUTF("Vào hành trang\nChọn bông tai Porata\nChọn mảnh hồn bông tai số lượng 99 cái\nvà đá xanh lam để nâng cấp\nSau đó chọn 'Nâng cấp'");
                                msg.writer().writeUTF("Ta sẽ phù phép\ncho bông tai Porata cấp 2 của ngươi\ncó 1 chỉ số ngẫu nhiên");
                                msg.writer().flush();
                                p.session.sendMessage(msg);
                                msg.cleanup();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (msg != null) {
                                    msg.cleanup();
                                }
                            }
                        } else if (select == 6) {
                            //                        doMenuArray(p,idNpc,"Nâng trang bị Thần Heart",new String[]{"Nâng\ntrang bị\nHeart","Mở chỉ số\nHeart"});
                        }
                        p.menuID = select;
                        break;
                    }
                    if (p.menuID == 0 && select == 0) { //bua 1 gio
                        TabItemShop[] test = Shop.getTabShop(21, 0).toArray(new TabItemShop[0]);
                        GameScr.UIshop(p, test);
                    } else if (p.menuID == 0 && select == 1) { // bua 8 gio
                        TabItemShop[] test = Shop.getTabShop(21, 1).toArray(new TabItemShop[1]);
                        GameScr.UIshop(p, test);
                    } else if (p.menuID == 0 && select == 2) { //bua 1 thang
                        TabItemShop[] test = Shop.getTabShop(21, 2).toArray(new TabItemShop[1]);
                        GameScr.UIshop(p, test);
                    } else if (p.menuID == 1 && select == 0) { //xac nhan nang cap item
                        Service.gI().sendUpLevelItem(p);
                        break;
                    } else if (p.menuID == 1 && select == 1) { //xac nhan nang cap item
                        Service.gI().sendUpLevelItem100(p);
                        break;
                    } else if (p.menuID == 2 && select == 0) { //xac nhan ep da
                        Service.gI().sendEpDaVun(p);
                        break;
                    } else if (p.menuID == 3 && select == 0) { //xac nhan ep da
                        Service.gI().sendEpNgocRong(p);
                        break;
                    } else if (p.menuID == 4 && select == 0) { //xac nhan nâng cấp
                        Service.gI().sendUpPorata(p);
                        break;
                    } else if (p.menuID == 5 && select == 0) { //xac nhan mo chi so bong tai
                        Service.gI().sendOpenOptionPorata(p);
                        break;
                    } else if (p.menuID == 6 && select == 0) { //xac nhan mo chi so bong tai
                        Message msg = null;
                        try {
                            msg = new Message(-81);
                            msg.writer().writeByte(0);
                            msg.writer().writeUTF("Vào hành trang\nChọn trang bị hủy diệt\nChọn mảnh trang bị thần, số lượng\n99 cái\nSau đó chọn 'Nâng cấp'");
                            msg.writer().writeUTF("Ta sẽ phù phép\ncho trang bị hủy diệt của ngươi\nthành trang bị thần Heart");
                            msg.writer().flush();
                            p.session.sendMessage(msg);
                            msg.cleanup();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (msg != null) {
                                msg.cleanup();
                            }
                        }
                        p.menuID = 7;
                        break;
                    } else if (p.menuID == 6 && select == 1) { //xac nhan mo chi so bong tai

                        break;
                    } else if (p.menuID == 7 && select == 0) {
                        //                    Service.gI().sendUpItemHeart(p);
                        break;
                    }
                    break;
                }
                //            if(p.menuID != -1)
                //            {
                //                if(p.menuID == 1 && select == 0)
                //                {
                //                    TabItemShop[] test = Shop.getTabShop(21, 0).toArray(new TabItemShop[0]);
                //                    GameScr.UIshop(p, test);
                //                }
                //            }
                //            if(select == 1){
                //                doMenuArray(p,idNpc,"Bùa của ta rất lợi hại. Mua xong có tác dụng ngay nhé, nhớ tranh thủ sử dụng, thoát game phí lắm. Mua càng nhiều thời gian giá càng rẻ!",new String[]{"Bùa\n1 tháng"});
                //                p.menuID = select;
                //            }
                //            break;
            }
            // ghi danh dai hoi vo thuat
            case 23: {
                if (p.map.id == 52) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            Service.chatNPC(p, (short) p.menuNPCID, "Lịch thi đấu trong ngày\bGiải Nhi đồng: 8,13,18h\bGiải Siêu cấp 1: 9,14,19h\bGiải Siêu cấp 2: 10,15,20h\bGiải Siêu cấp 3: 11,16,21h\bGiải Ngoại hạng: 12,17,22,23h\nGiải thưởng khi thắng mỗi vòng\bGiải Nhi đồng: 2 ngọc\bGiải Siêu cấp 1: 4 ngọc\bGiải Siêu cấp 2: 6 ngọc\bGiải Siêu cấp 3: 8 ngọc\bGiải Ngoại hạng: 10.000 vàng\bVô địch: 5 viên đá nâng cấp\nVui lòng đến đúng giờ để đăng ký thi đấu");
                        }
                        if (select == 1) {
                            if (DaiHoiManager.gI().openDHVT && (System.currentTimeMillis() <= DaiHoiManager.gI().tOpenDHVT)) {
                                String nameDH = DaiHoiManager.gI().nameRoundDHVT();
                                doMenuArray(p, idNpc, "Hiện đang có giải đấu " + nameDH + " bạn có muốn đăng ký không?", new String[]{"Giải\n" + nameDH + "\n(" + DaiHoiManager.gI().costRoundDHVT() + ")", "Từ chối"});
                            } else {
                                Service.chatNPC(p, (short) p.menuNPCID, "Đã hết thời gian đăng ký, hãy quay lại ở giải sau");
                                break;
                            }
                        }
                        p.menuID = select;
                        break;
                    }
                    if (p.menuID == 1) {
                        if (select == 0) {
                            if (DaiHoiService.gI().canRegisDHVT(p.sumTiemNang)) {
                                if (DaiHoiManager.gI().lstIDPlayers.size() < 256) {
                                    if (DaiHoiManager.gI().typeDHVT == (byte) 5 && p.vang >= 10000) {
                                        if (DaiHoiManager.gI().isAssignDHVT(p.id)) {
                                            p.sendAddchatYellow("Bạn đã đăng ký tham gia đại hội võ thuật rồi");
                                        } else {
                                            p.vang -= 10000;
                                            Service.gI().buyDone(p);
                                            Service.chatNPC(p, (short) p.menuNPCID, "Bạn đã đăng ký thành công, nhớ có mặt tại đây trước giờ thi đấu");
                                            //                    DaiHoiManager.gI().lstPlayers.add(p);
                                            DaiHoiManager.gI().lstIDPlayers.add(p.id);
                                        }
                                    } else if (DaiHoiManager.gI().typeDHVT > (byte) 0 && DaiHoiManager.gI().typeDHVT < (byte) 5 && p.ngoc >= (int) (2 * DaiHoiManager.gI().typeDHVT)) {
                                        if (DaiHoiManager.gI().isAssignDHVT(p.id)) {
                                            p.sendAddchatYellow("Bạn đã đăng ký tham gia đại hội võ thuật rồi");
                                        } else {
                                            p.ngoc -= (int) (2 * DaiHoiManager.gI().typeDHVT);
                                            Service.gI().buyDone(p);
                                            Service.chatNPC(p, (short) p.menuNPCID, "Bạn đã đăng ký thành công, nhớ có mặt tại đây trước giờ thi đấu");
                                            //                    DaiHoiManager.gI().lstPlayers.add(p);
                                            DaiHoiManager.gI().lstIDPlayers.add(p.id);
                                        }
                                    } else {
                                        p.sendAddchatYellow("Không đủ vàng ngọc để đăng ký thi đấu");
                                    }
                                } else {
                                    p.sendAddchatYellow("Hiện tại đã đạt tới số lượng người đăng ký tối đa, xin hãy chờ đến giải sau");
                                }

                            } else {
                                p.sendAddchatYellow("Bạn không đủ điều kiện tham gia giải này, hãy quay lại vào giải phù hợp");
                            }
                        }
                        break;
                    }
                }
                break;
            }
            case 20: { //KAIO
                if (p.map.id == 48) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            p.zone.goMapTransport(p, 45);
                        }
                        if (select == 1) {
                            p.zone.goMapTransport(p, 50);
                        }
                        if (select == 2) {
                            break;
                        }
                        p.menuID = select;
                        break;
                    }
                }
                break;
            }
            case 19: { //THUONG DE
                if (p.map.id == 45) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            p.zone.goMapTransport(p, 48);
                        }
                        if (select == 1) { //Open UI QUAY NGOC
                            if (p.ItemQuay.size() > 0) {
                                doMenuArray(p, idNpc, "Bạn có thể chọn từ 1 đến 7 viên ngọc, giá mỗi viên là 25tr Vàng\nƯu tiên dùng vé quay trước", new String[]{"Top 100", "Đồng ý", "Vòng quay\nĐặc biệt", "Rương phụ\nĐang có " + p.ItemQuay.size() + "\nmón", "Đóng"});
                            } else {
                                doMenuArray(p, idNpc, "Bạn có thể chọn từ 1 đến 7 viên ngọc, giá mỗi viên là 25tr Vàng\nƯu tiên dùng vé quay trước", new String[]{"Top 100", "Đồng ý", "Vòng quay\nĐặc biệt", "Rương phụ\nĐang có", "Đóng"});
                            }
                        }
                        p.menuID = select;
                        break;
                    }
                    if (p.menuID == 1) {
                        if (select == 0) {
                            break;
                        } else if (select == 1) { //QUAY NGOC THUONG
                            LuckyService.gI().loadUILucky(p);
                        } else if (select == 2) { //QUAY NGOC DAC BIET
                            LuckyService.gI().loadUILucky(p);
                        }
                        if (p.ItemQuay.size() > 0) {
                            if (select == 3) {
                                p.openItemQuay = true;
                                LuckyService.gI().openItemQuay(p);
                            } else if (select == 4) {
                                break;
                            }
                        } else {
                            if (select == 3) {
                                break;
                            }
                        }
                    }
                }
                break;
            }
            //        case 16:{
            //            if(select == 0){
            //                TabItemShop[] test = Shop.getTabShop(16, p.gender).toArray(new TabItemShop[0]);
            //                GameScr.UIshop(p, test);
            //                break;
            //            }
            //            break;
            //        }
            case 13: {
                if (p.map.id == 5) {
                    if (p.menuID != -1) {
                        // if (p.menuID == 1 && select == 0) {
                        //     //CHECK NHIEM VU VONG 2 DHVT
                        //     if (p.taskId == (short) 8 && p.crrTask.index == (byte) 1 && Server.gI().isPassDHVT) {
                        //         TaskService.gI().updateCountTask(p);
                        //     }
                        //     break;
                        // }
                        // if (p.menuID == 1 && select == 1) {
                        //     //CHECK NHIEM VU VONG 2 DHVT
                        //     if (p.taskId == (short) 14 && p.crrTask.index == (byte) 1) {
                        //         TaskService.gI().updateCountTask(p);
                        //     }
                        //     break;
                        // }
                        if (p.menuID == 1 && select == 0) { //titan
                            Service.chatNPC(p, (short) 24, HelperDAO.getTopPower());
                            break;
                        } else if (p.menuID == 1 && select == 1) {//ruby
                            Service.chatNPC(p, (short) 24, HelperDAO.getTopCard());
                            break;
                        // } else if (p.menuID == 1 && select == 2) {//
                        //     Service.chatNPC(p, (short) 24, HelperDAO.getTopPoint());
                        //     break;
                        // } else if (p.menuID == 2 && select == 3) {//saphia
                        //     //                        Item TITAN = ItemSell.getItemNotSell(221);
                        //     //                        p.addItemToBagx99(TITAN);
                        //     //                        Service.gI().updateItemBag(p);
                        //     //                        p.sendAddchatYellow("Nhận thành công đá Saphia");
                        //     Service.chatNPC(p, (short) 24, HelperDAO.getTopsukien());

                        //     break;
                        // } else if (p.menuID == 2 && select == 4) {//luc bao
                        //     //                        Item TITAN = ItemSell.getItemNotSell(220);
                        //     //                        p.addItemToBagx99(TITAN);
                        //     //                        Service.gI().updateItemBag(p);
                        //     //                        p.sendAddchatYellow("Nhận thành công đá Lục Bảo");
                        //     break;
                        } else if (p.menuID == 2 && select == 0) { //GIAI TAN BANG HOI
                            if (Server.gI().isServer == (byte) 1) {
                                ClanService.gI().distoryClan(p);
                                ClanService.gI().updateDataClanToDB();
                            } else {
                                p.sendAddchatYellow("Bạn không có bang hội!");
                            }
                            break;
                        } else if (p.menuID == 2 && select == 1) { //KHU VUC BANG HOI
                            GotoMap(p, 153);
                            break;
                        }
                        if (p.menuID == 3) {
                            if (select == 0 && p.balance >= 10000) {
                                if (HelperDAO.payMoneyDB(p, 10000, false)) {
                                    Item thoivangTemp = ItemSell.getItemNotSell(457);
                                    if (thoivangTemp != null) {
                                        Item thoivang = new Item(thoivangTemp);
                                        thoivang.quantity = 50;
                                        p.addItemToBag(thoivang);
                                        Service.gI().updateItemBag(p);
                                        p.sendAddchatYellow("Bạn vừa nhận được 50 thỏi vàng");
                                    } else {
                                        p.sendAddchatYellow("Đổi vàng thất bại");
                                    }
                                } else {
                                    p.sendAddchatYellow("Đổi vàng thất bại");
                                }
                            } else if (select == 1 && p.balance >= 20000) {
                                if (HelperDAO.payMoneyDB(p, 20000, false)) {
                                    Item thoivangTemp = ItemSell.getItemNotSell(457);
                                    if (thoivangTemp != null) {
                                        Item thoivang = new Item(thoivangTemp);
                                        thoivang.quantity = 100;
                                        p.addItemToBag(thoivang);
                                        Service.gI().updateItemBag(p);
                                        p.sendAddchatYellow("Bạn vừa nhận được 100 thỏi vàng");
                                    } else {
                                        p.sendAddchatYellow("Đổi vàng thất bại");
                                    }
                                } else {
                                    p.sendAddchatYellow("Đổi vàng thất bại");
                                }
                            } else if (select == 2 && p.balance >= 30000) {
                                if (HelperDAO.payMoneyDB(p, 30000, false)) {
                                    Item thoivangTemp = ItemSell.getItemNotSell(457);
                                    if (thoivangTemp != null) {
                                        Item thoivang = new Item(thoivangTemp);
                                        thoivang.quantity = 150;
                                        p.addItemToBag(thoivang);
                                        Service.gI().updateItemBag(p);
                                        p.sendAddchatYellow("Bạn vừa nhận được 150 thỏi vàng");
                                    } else {
                                        p.sendAddchatYellow("Đổi vàng thất bại");
                                    }

                                } else {
                                    p.sendAddchatYellow("Đổi vàng thất bại");
                                }
                            } else if (select == 3 && p.balance >= 50000) {
                                if (HelperDAO.payMoneyDB(p, 50000, false)) {
                                    Item thoivangTemp = ItemSell.getItemNotSell(457);
                                    if (thoivangTemp != null) {
                                        Item thoivang = new Item(thoivangTemp);
                                        thoivang.quantity = 250;
                                        p.addItemToBag(thoivang);
                                        Service.gI().updateItemBag(p);
                                        p.sendAddchatYellow("Bạn vừa nhận được 250 thỏi vàng");
                                    } else {
                                        p.sendAddchatYellow("Đổi vàng thất bại");
                                    }

                                } else {
                                    p.sendAddchatYellow("Đổi vàng thất bại");
                                }
                            } else if (select == 4 && p.balance >= 100000) {
                                if (HelperDAO.payMoneyDB(p, 100000, false)) {
                                    Item thoivangTemp = ItemSell.getItemNotSell(457);
                                    if (thoivangTemp != null) {
                                        Item thoivang = new Item(thoivangTemp);
                                        thoivang.quantity = 500;
                                        p.addItemToBag(thoivang);
                                        Service.gI().updateItemBag(p);
                                        p.sendAddchatYellow("Bạn vừa nhận được 500 thỏi vàng");
                                    } else {
                                        p.sendAddchatYellow("Đổi vàng thất bại");
                                    }
                                } else {
                                    p.sendAddchatYellow("Đổi vàng thất bại");
                                }
                            } else if (select == 5 && p.balance >= 200000) {
                                if (HelperDAO.payMoneyDB(p, 200000, false)) {
                                    Item thoivangTemp = ItemSell.getItemNotSell(457);
                                    if (thoivangTemp != null) {
                                        Item thoivang = new Item(thoivangTemp);
                                        thoivang.quantity = 1000;
                                        p.addItemToBag(thoivang);
                                        Service.gI().updateItemBag(p);
                                        p.sendAddchatYellow("Bạn vừa nhận được 1000 thỏi vàng");
                                    } else {
                                        p.sendAddchatYellow("Đổi vàng thất bại");
                                    }

                                } else {
                                    p.sendAddchatYellow("Đổi vàng thất bại");
                                }
                            } else if (select == 6 && p.balance >= 500000) {
                                if (HelperDAO.payMoneyDB(p, 500000, false)) {
                                    Item thoivangTemp = ItemSell.getItemNotSell(457);
                                    if (thoivangTemp != null) {
                                        Item thoivang = new Item(thoivangTemp);
                                        thoivang.quantity = 2500;
                                        p.addItemToBag(thoivang);
                                        Service.gI().updateItemBag(p);
                                        p.sendAddchatYellow("Bạn vừa nhận được 2500 thỏi vàng");
                                    } else {
                                        p.sendAddchatYellow("Đổi vàng thất bại");
                                    }
                                } else {
                                    p.sendAddchatYellow("Đổi vàng thất bại");
                                }
                            } else if (select == 7 && p.balance >= 1000000) {
                                if (HelperDAO.payMoneyDB(p, 1000000, false)) {
                                    Item thoivangTemp = ItemSell.getItemNotSell(457);
                                    if (thoivangTemp != null) {
                                        Item thoivang = new Item(thoivangTemp);
                                        thoivang.quantity = 5000;
                                        p.addItemToBag(thoivang);
                                        Service.gI().updateItemBag(p);
                                        p.sendAddchatYellow("Bạn vừa nhận được 5000 thỏi vàng");
                                    } else {
                                        p.sendAddchatYellow("Đổi vàng thất bại");
                                    }

                                } else {
                                    p.sendAddchatYellow("Đổi vàng thất bại");
                                }
                            } else {
                                p.sendAddchatYellow("Số dư không đủ để thực hiện thao tác");
                            }
                            break;
                        }
                    }
                    // if (select == 1) {
                    //     doMenuArray(p, idNpc, "Ta có thể giúp gì cho con", new String[]{"NV 7s", "NV Truyện"});
                    //     p.menuID = select;
                    // }
                    if (select == 1) {
                        doMenuArray(p, idNpc, "Ta có thể giúp gì cho con", new String[]{"BXH Sức mạnh", "BXH Nạp thẻ"});
                        p.menuID = select;
                    } else if (select == 2) {
                        doMenuArray(p, idNpc, "Ta có thể giúp gì cho con", new String[]{"Giải tán\nbang hội", "Khu vực\nbang hội"});
                        p.menuID = select;
                    } else if (select == 3) {
                        doMenuArray(p, idNpc, "Bạn đang có " + p.balance + " , bạn muốn làm gì ?", new String[]{"50 Thỏi Vàng\n(10.000)",
                            "100 Thỏi Vàng\n(20.000)", "150 Thỏi Vàng\n(30.000)", "250 Thỏi Vàng\n(50.000)", "500 Thỏi Vàng\n(100.000)",
                            "1000 Thỏi Vàng\n(200.000)",
                            "2500 Thỏi Vàng\n(500.000)", "5000 Thỏi Vàng\n(1.000.000)",});
                        p.menuID = select;;
                    }
                }
                break;
            }
            case 12: {//CUI DI CHUYEN
                if (p.map.id == 19) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            p.zone.goMapTransport(p, 105);
                            //                        GotoMap(p,109);
                        }
                        if (select == 1) {
                            p.zone.goMapTransport(p, 68);
                            //                        GotoMap(p,68);
                        }
                        if (select == 2) {
                            break;
                        }
                        if (select == 3) {
                            if (p.taskId == (short) 21 && p.crrTask.index == (byte) 0) {
                                doMenuArray(p, idNpc, "Ta vừa thấy tên Kuku, ngươi có muốn tới chỗ hắn không", new String[]{"OK\n100 ngọc", "Từ chối"});
                                p.menuID = select;
                            } else if (p.taskId == (short) 21 && p.crrTask.index == (byte) 1) {
                                doMenuArray(p, idNpc, "Ta vừa thấy tên Mập Đầu Đinh, ngươi có muốn tới chỗ hắn không", new String[]{"OK\n100 ngọc", "Từ chối"});
                                p.menuID = select;
                            } else if (p.taskId == (short) 21 && p.crrTask.index == (byte) 2) {
                                doMenuArray(p, idNpc, "Ta vừa thấy tên Rambo, ngươi có muốn tới chỗ hắn không", new String[]{"OK\n100 ngọc", "Từ chối"});
                                p.menuID = select;
                            }
                            break;
                        }
                    } else if (p.menuID == 3) {
                        if (select == 0 && p.taskId == (short) 21) {
                            if (p.crrTask.index == (byte) 0) {
                                if (Server.gI().mapKUKU != 0) {
                                    if (p.petfucus == 1) {
                                        p.zone.leaveDetu(p, p.detu);
                                    }
                                    if (p.pet2Follow == 1 && p.pet != null) {
                                        p.zone.leavePETTT(p.pet);
                                    }
                                    p.zone.leave(p);
                                    int _rdLocation = Util.nextInt(0, (Server.gI().maps[Server.gI().mapKUKU].template.arMobid.length - 1)); //get index mob random
                                    p.x = Server.gI().maps[Server.gI().mapKUKU].template.arrMobx[_rdLocation];
                                    p.y = Server.gI().maps[Server.gI().mapKUKU].template.arrMoby[_rdLocation];
                                    Server.gI().maps[Server.gI().mapKUKU].area[Server.gI().khuKUKU].EnterCapsule(p);
                                } else {
                                    Service.chatNPC(p, (short) p.menuNPCID, "Kuku chưa xuất hiện");
                                }
                            } else if (p.crrTask.index == (byte) 1) {
                                if (Server.gI().mapMDD != 0) {
                                    if (p.petfucus == 1) {
                                        p.zone.leaveDetu(p, p.detu);
                                    }
                                    if (p.pet2Follow == 1 && p.pet != null) {
                                        p.zone.leavePETTT(p.pet);
                                    }
                                    p.zone.leave(p);
                                    int _rdLocation = Util.nextInt(0, (Server.gI().maps[Server.gI().mapMDD].template.arMobid.length - 1)); //get index mob random
                                    p.x = Server.gI().maps[Server.gI().mapMDD].template.arrMobx[_rdLocation];
                                    p.y = Server.gI().maps[Server.gI().mapMDD].template.arrMoby[_rdLocation];
                                    Server.gI().maps[Server.gI().mapMDD].area[Server.gI().khuMDD].EnterCapsule(p);
                                } else {
                                    Service.chatNPC(p, (short) p.menuNPCID, "Mập Đầu Đinh chưa xuất hiện");
                                }
                            } else if (p.crrTask.index == (byte) 2) {
                                if (Server.gI().mapRAMBO != 0) {
                                    if (p.petfucus == 1) {
                                        p.zone.leaveDetu(p, p.detu);
                                    }
                                    if (p.pet2Follow == 1 && p.pet != null) {
                                        p.zone.leavePETTT(p.pet);
                                    }
                                    p.zone.leave(p);
                                    int _rdLocation = Util.nextInt(0, (Server.gI().maps[Server.gI().mapRAMBO].template.arMobid.length - 1)); //get index mob random
                                    p.x = Server.gI().maps[Server.gI().mapRAMBO].template.arrMobx[_rdLocation];
                                    p.y = Server.gI().maps[Server.gI().mapRAMBO].template.arrMoby[_rdLocation];
                                    Server.gI().maps[Server.gI().mapRAMBO].area[Server.gI().khuRAMBO].EnterCapsule(p);
                                } else {
                                    Service.chatNPC(p, (short) p.menuNPCID, "Rambo chưa xuất hiện");
                                }
                            }
                        }
                        break;
                    }
                } else if (p.map.id == 68) {
                    if (select == 0) {
                        p.zone.goMapTransport(p, 19);
                    }
                    if (select == 1) {
                        break;
                    }
                } else if (p.map.id == 26) {
                    if (select == 0) {
                        p.zone.goMapTransport(p, 24);
                    }
                    if (select == 1) {
                        p.zone.goMapTransport(p, 25);
                    }
                    if (select == 2) {
                        break;
                    }
                }
                break;
            }
            case 11: {
                if (p.map.id == 25) {
                    if (select == 0) {
                        p.zone.goMapTransport(p, 24);
                    }
                    if (select == 1) {
                        p.zone.goMapTransport(p, 26);
                    }
                    if (select == 2) {
                        break;
                    }
                }
                break;
            }
            case 10: {
                if (p.map.id == 24) {
                    if (select == 0) {
                        p.zone.goMapTransport(p, 25);
                    }
                    if (select == 1) {
                        p.zone.goMapTransport(p, 26);
                    }
                    if (select == 2) {
                        break;
                    }
                }
                break;
            }
            case 9: {
                if (p.map.id == 14) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            TabItemShop[] test = Shop.getTabShop(9, 0).toArray(new TabItemShop[0]);
                            GameScr.UIshop(p, test);
                        } else if (select == 1) {
                            if (p.taskId == (short) 8 && p.crrTask.index == (byte) 1 && Server.gI().isPassDHVT) {
                                TaskService.gI().updateCountTask(p);
                            }
                            break;
                        }
                    }
                }
                break;
            }
            case 8: { //DENDE
                if (p.map.id == 7) {
                    if (p.imgNRSD == (byte) 53) {
                        if (select == 1) {
                            if (p.nrNamec == 353) {
                                //                        if(Server.gI().firstNrNamec && p.zone.canCallDragonNamec(p)) {
                                //                            Server.gI().firstNrNamec = false;
                                //                            Server.gI().timeNrNamec = System.currentTimeMillis() + 600000;
                                //                            Service.chatNPC(p, (short)idNpc, "Ngọc bẩn quá, xin chờ em 9 phút nữa để lau bóng ngọc, gọi Rồng mới hiển linh");
                                //                            break;
                                //                        }
                                //                            if(!Server.gI().firstNrNamec) {
                                //                        if((Server.gI().timeNrNamec - System.currentTimeMillis()) > 0) {
                                //                            Service.chatNPC(p, (short)idNpc, "Ngọc bẩn quá, xin chờ em "+ (int)((Server.gI().timeNrNamec - System.currentTimeMillis())/60000) +" phút nữa để lau bóng ngọc, gọi Rồng mới hiển linh");
                                //                            break;
                                //                        } else { //GOI RONG NAMEC
                                if (p.zone.canCallDragonNamec(p)) {
                                    Server.gI().tOpenNrNamec = System.currentTimeMillis() + 86400000;
                                    Server.gI().firstNrNamec = true;
                                    Server.gI().timeNrNamec = 0;

                                    Service.gI().doneDragonNamec(p);
                                    //INIT NGOC RONG HOA THACH
                                    Service.gI().initNgocRongNamec((byte) 1);
                                    //TIMER TASK INIT LAI NGOC RONG NAMEC
                                    Service.gI().reInitNrNamec((long) 86399000);
                                    Service.gI().callDragonNamec(p);
                                    //UI CHON DIEU UOC NGOC RONG NAMEK
                                    p.menuID = -1;
                                    p.menuNPCID = 27;
                                    Menu.doMenuArray(p, 27, "Ta sẽ ban cho cả bang hội các ngươi 1 điều ước, ngươi có 5 phút, hãy\nsuy nghĩ thật kỹ trước khi quyết định", new String[]{"Đá bảo vệ\nx10", "Berry đeo\nlưng"});
                                    break;
                                }
                                //                        }
                                //                            break;
                                //                        } else {
                                //                               Service.chatNPC(p, (short)idNpc, "Ngọc bẩn quá, xin chờ em 9 phút nữa để lau bóng ngọc, gọi Rồng mới hiển linh");
                                //                            break;
                                //                            }
                            } else {
                                p.sendAddchatYellow("Anh phải có viên ngọc rồng Namec 1 sao");
                            }
                        }
                    } else if (p.menuID == -1) {
                        if (select == 0) {
                            TabItemShop[] test = Shop.getTabShop(8, 2).toArray(new TabItemShop[0]);
                            GameScr.UIshop(p, test);
                        } else if (select == 1) {
                            if (p.taskId == (short) 8 && p.crrTask.index == (byte) 1 && Server.gI().isPassDHVT) {
                                TaskService.gI().updateCountTask(p);
                            }
                            break;
                        }
                    }
                }
                break;
            }
            case 7: {
                if (p.map.id == 0) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            TabItemShop[] test = Shop.getTabShop(7, 1).toArray(new TabItemShop[0]);
                            GameScr.UIshop(p, test);
                        } else if (select == 1) {
                            if (p.taskId == (short) 8 && p.crrTask.index == (byte) 1 && Server.gI().isPassDHVT) {
                                TaskService.gI().updateCountTask(p);
                            }
                            break;
                        }
                    }
                }
                break;
            }
            case 4: {
                if (p.map.id == 21 || p.map.id == 22 || p.map.id == 23) {
                    if (p.upMagicTree) { //NANG CAP DAU THAN
                        if (p.menuID == -1) {
                            if (select == 0) {
                                doMenuArray(p, idNpc, "Bạn có chắc chắn muốn nâng cấp nhanh?", new String[]{"OK", "Từ chối"});
                            }
                            if (select == 1) {
                                doMenuArray(p, idNpc, "Bạn có chắc chắn muốn hủy nâng cấp?", new String[]{"OK", "Từ chối"});
                            }
                            p.menuID = select;
                            break;
                        }
                        if (p.menuID == 0 && select == 0) {
                            if (p.ngoc >= ngocUpMagicTree(p.levelTree) && p.levelTree < (byte) 10) {
                                p.ngoc -= ngocUpMagicTree(p.levelTree);
                                Service.gI().buyDone(p);
                                p.levelTree = (byte) (p.levelTree + 1) > (byte) 10 ? (byte) 10 : (byte) (p.levelTree + 1);
                                p.upMagicTree = false;
                                p.maxBean = (byte) (p.maxBean + 2) > (byte) 23 ? (byte) 23 : (byte) (p.maxBean + 2);
                                p.lastTimeTree = System.currentTimeMillis();
                                Service.gI().MagicTree(p, (byte) 0);
                            } else {
                                p.sendAddchatYellow("Không đủ ngọc để nâng cấp đậu thần");
                            }
                            break;
                        }
                        if (p.menuID == 1 && select == 0) {
                            p.vang -= goldUpMagicTree(p.levelTree) / 2;
                            Service.gI().buyDone(p);
                            p.upMagicTree = false;
                            p.lastTimeTree = System.currentTimeMillis();
                            Service.gI().MagicTree(p, (byte) 0);
                            p.sendAddchatYellow("Đã hủy nâng cấp đậu thần");
                            break;
                        }
                    } else {
                        if (p.menuID == -1) {
                            if (select == 0) {
                                int countDAUHT = 0;
                                //COUNT DAU THAN TRONG HANH TRANG
                                for (byte i = 0; i < p.ItemBag.length; i++) {
                                    if (p.ItemBag[i] != null && p.ItemBag[i].template.type == (byte) 6) {
                                        countDAUHT += p.ItemBag[i].quantity;
                                    }
                                }
                                int maxDauTheo = 20;
                                if (p.gender == (byte) 1) {
                                    maxDauTheo = 30;
                                }
                                if (countDAUHT < maxDauTheo) {
                                    byte countThu = (byte) (maxDauTheo - countDAUHT) > p.maxBean ? p.maxBean : (byte) (maxDauTheo - countDAUHT);
                                    p.currentBean -= countThu;
                                    p.lastTimeTree = System.currentTimeMillis() - (long) (p.currentBean * p.levelTree * 60000);
                                    int itemDAUTHAN = 13;
                                    if (p.levelTree == (byte) 2) {
                                        itemDAUTHAN = 60;
                                    } else if (p.levelTree == (byte) 3) {
                                        itemDAUTHAN = 61;
                                    } else if (p.levelTree == (byte) 4) {
                                        itemDAUTHAN = 62;
                                    } else if (p.levelTree == (byte) 5) {
                                        itemDAUTHAN = 63;
                                    } else if (p.levelTree == (byte) 6) {
                                        itemDAUTHAN = 64;
                                    } else if (p.levelTree == (byte) 7) {
                                        itemDAUTHAN = 65;
                                    } else if (p.levelTree == (byte) 8) {
                                        itemDAUTHAN = 352;
                                    } else if (p.levelTree == (byte) 9) {
                                        itemDAUTHAN = 523;
                                    } else if (p.levelTree == (byte) 10) {
                                        itemDAUTHAN = 595;
                                    }
                                    byte indexDAU = p.getIndexBagid(itemDAUTHAN);
                                    if (indexDAU != -1) {
                                        p.ItemBag[indexDAU].quantity += (int) countThu;
                                    } else {
                                        byte indexNOT = p.getIndexBagNotItem();
                                        if (p.getBagNull() == 0) {
                                            p.sendAddchatYellow("Hành trang không đủ chỗ trống!");
                                        } else {
                                            ItemSell dauThan = ItemSell.getItemSell(itemDAUTHAN, (byte) 1);
                                            Item dauThuHoach = new Item(dauThan.item);
                                            dauThuHoach.quantity = (int) countThu;
                                            p.ItemBag[indexNOT] = dauThuHoach;
                                        }
                                    }
                                    Service.gI().updateItemBag(p);
                                    p.sendAddchatYellow("Bạn vừa thu hoạch thành công " + countThu + " viên đậu thần cấp " + p.levelTree);
                                } else {
                                    p.sendAddchatYellow("Số đậu trong hành trang đã đầy");
                                }
                                Service.gI().MagicTree(p, (byte) 0);

                                p.getIndexItemBagByType((byte) 6); //GET DAU THAN TRONG HANH TRANG

                            }
                            if (p.levelTree >= (byte) 10) {
                                if (select == 1) { //THU DAU NHANH
                                    if (p.ngoc >= ngocThuDauThan(p.levelTree)) {
                                        p.ngoc -= ngocThuDauThan(p.levelTree);
                                        Service.gI().buyDone(p);
                                        p.currentBean = p.maxBean;
                                        p.lastTimeTree = 0;
                                        Service.gI().MagicTree(p, (byte) 0);
                                    } else {
                                        p.sendAddchatYellow("Không đủ ngọc để nâng cấp đậu thần");
                                    }
                                }
                            } else {
                                if (select == 1) {
                                    doMenuArray(p, idNpc, "Bạn có chắc chắn muốn nâng cấp đậu thần?", new String[]{"OK", "Từ chối"});
                                }
                                if (select == 2) { //THU DAU NHANH
                                    if (p.ngoc >= ngocThuDauThan(p.levelTree)) {
                                        p.ngoc -= ngocThuDauThan(p.levelTree);
                                        Service.gI().buyDone(p);
                                        p.currentBean = p.maxBean;
                                        p.lastTimeTree = 0;
                                        Service.gI().MagicTree(p, (byte) 0);
                                    } else {
                                        p.sendAddchatYellow("Không đủ ngọc để nâng cấp đậu thần");
                                    }
                                }
                            }
                            p.menuID = select;
                            break;
                        }
                        if (p.levelTree < (byte) 10) {
                            if (p.menuID == 1) {
                                if (select == 0) { //NANG CAP DAU THAN
                                    if (p.vang >= goldUpMagicTree(p.levelTree) && p.levelTree < (byte) 10) {
                                        p.vang -= goldUpMagicTree(p.levelTree);
                                        Service.gI().buyDone(p);
                                        //                        p.levelTree += (byte)1;
                                        p.upMagicTree = true;
                                        p.lastTimeTree = System.currentTimeMillis() + (long) (timeUpMagicTree(p.levelTree) * 1000);
                                        Service.gI().MagicTree(p, (byte) 0);
                                    } else {
                                        p.sendAddchatYellow("Không đủ vàng để nâng cấp đậu thần");
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                break;
            }
            case 2: {
                if (p.map.id == 22) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            Service.gI().clientInput(p, "Nhập giftcode", "Giftcode", (byte) 0);
                            break;
                        }
                        if (select == 1) {
                            if (p.ngoc < 10000000) {
                                p.ngoc = p.ngoc + 100000;
                                Service.gI().buyDone(p);
                                p.sendAddchatYellow("Nhận thành công 100k ngọc");
                            } else {
                                p.sendAddchatYellow("Vui lòng sử dụng hết ngọc");
                            }
                            break;
                        }
                        // if (select == 2) {
                        //     if (p.checktanthu == 0) {
                        //         p.checktanthu += 1;

                        //         Item tv = ItemSell.getItemNotSell(397);

                        //         p.addItemToBag(tv);
                        //         p.sendAddchatYellow("Nhận thành công " + tv.template.name);
                        //         PlayerDAO.updateDB(p);
                        //         //PlayerDAO.checktanthu(p.id, 1);
                        //         Service.gI().updateItemBag(p);
                        //         break;
                        //     } else {
                        //         p.sendAddchatYellow("Đã nhận rồi");
                        //     }
                        // }
                        if (p.hasTrungMabu && select == 2) {
                            doMenuArray(p, idNpc, "Con có muốn nhận đệ tử Mabư, sẽ thay thế đệ tử hiện tại nếu có", new String[]{"OK", "Xóa đệ\nMabư", "Đóng"});
                            p.menuID = select;
                            break;
                        }
                    }
                    if (p.hasTrungMabu) {
                        if (p.menuID == 2) {
                            if (select == 0) {
                                p.statusPet = 0;
                                p.detu = null;
                                p.detu = new Detu();
                                p.detu.initDetuMabu(p.detu, p.gender);
                                p.detu.id = (-100000 - p.id);
                                p.hasTrungMabu = false;
                            } else if (select == 1) {
                                p.hasTrungMabu = false;
                            } else {
                                break;
                            }
                        }
                    }
                }
                break;
            }
            case 1: {
                if (p.map.id == 23) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            Service.gI().clientInput(p, "Nhập giftcode", "Giftcode", (byte) 0);
                            break;
                        }

                        if (select == 1) {
                            if (p.ngoc < 1000000) {
                                p.ngoc = p.ngoc + 10000;
                                Service.gI().buyDone(p);
                                p.sendAddchatYellow("Nhận thành công 10k ngọc");
                            } else {
                                p.sendAddchatYellow("Vui lòng sử dụng hết ngọc");
                            }
                            break;
                        }
                        // if (select == 2) {
                        //     if (p.checktanthu == 0) {
                        //         p.checktanthu += 1;

                        //         Item tv = ItemSell.getItemNotSell(397);

                        //         p.addItemToBag(tv);
                        //         p.sendAddchatYellow("Nhận thành công " + tv.template.name);
                        //         PlayerDAO.updateDB(p);
                        //         //PlayerDAO.checktanthu(p.id, 1);
                        //         Service.gI().updateItemBag(p);
                        //         break;
                        //     } else {
                        //         p.sendAddchatYellow("Đã nhận rồi");
                        //     }
                        // }
                        if (p.hasTrungMabu && select == 2) {
                            doMenuArray(p, idNpc, "Con có muốn nhận đệ tử Mabư, sẽ thay thế đệ tử hiện tại nếu có", new String[]{"OK", "Xóa đệ\nMabư", "Đóng"});
                            p.menuID = select;
                            break;
                        }
                    }
                    if (p.hasTrungMabu) {
                        if (p.menuID == 2) {
                            if (select == 0) {
                                p.statusPet = 0;
                                p.detu = null;
                                p.detu = new Detu();
                                p.detu.initDetuMabu(p.detu, p.gender);
                                p.detu.id = (-100000 - p.id);
                                p.hasTrungMabu = false;
                            } else if (select == 1) {
                                p.hasTrungMabu = false;
                            } else {
                                break;
                            }
                        }
                    }
                }
                break;
            }
            case 0: {
                if (p.map.id == 21) {
                    if (p.menuID == -1) {
                        if (select == 0) {
                            Service.gI().clientInput(p, "Nhập giftcode", "Giftcode", (byte) 0);
                            break;
                        }

                        if (select == 1) {
                            if (p.ngoc < 1000000) {
                                p.ngoc = p.ngoc + 10000;
                                Service.gI().buyDone(p);
                                p.sendAddchatYellow("Nhận thành công 10k ngọc");
                            } else {
                                p.sendAddchatYellow("Vui lòng sử dụng hết ngọc");
                            }
                            break;
                        }
                        // if (select == 2) {
                        //     if (p.checktanthu == 0) {
                        //         p.checktanthu += 1;

                        //         Item tv = ItemSell.getItemNotSell(397);

                        //         p.addItemToBag(tv);
                        //         p.sendAddchatYellow("Nhận thành công " + tv.template.name);
                        //         PlayerDAO.updateDB(p);
                        //         //PlayerDAO.checktanthu(p.id, 1);
                        //         Service.gI().updateItemBag(p);
                        //         break;
                        //     } else {
                        //         p.sendAddchatYellow("Đã nhận rồi");
                        //     }

                        // }
                        if (p.hasTrungMabu && select == 2) {
                            doMenuArray(p, idNpc, "Con có muốn nhận đệ tử Mabư, sẽ thay thế đệ tử hiện tại nếu có", new String[]{"OK", "Xóa đệ\nMabư", "Đóng"});
                            p.menuID = select;
                            break;
                        }
                    }
                    if (p.hasTrungMabu) {
                        if (p.menuID == 2) {
                            if (select == 0) {
                                p.statusPet = 0;
                                p.detu = null;
                                p.detu = new Detu();
                                p.detu.initDetuMabu(p.detu, p.gender);
                                p.detu.id = (-100000 - p.id);
                                p.hasTrungMabu = false;
                            } else if (select == 1) {
                                p.hasTrungMabu = false;
                            } else {
                                break;
                            }
                        }
                    }
                }
                break;
            }
            default: {
                Service.gI().sendTB(p.session, 0, "Chức Năng Đang Được Cập Nhật " + idNpc, 0);
                break;
            }
        }
        m.cleanup();
    }

    public void GotoMap(Player p, int id) {
        Map maptele = MainManager.getMapid(id);
        Controller.getInstance().teleportToMAP(p, maptele);
    }

    public void menuHandler(Player p, Message m) throws IOException, SQLException, InterruptedException {
        byte idNPC = m.reader().readByte();// ID NPC
        byte menuID = m.reader().readByte();// Lớp nút 1
        byte select = m.reader().readByte();// Lớp nút 2
//         System.out.println("menuID: "+ p.menuID);
//         System.out.println("menuNPCID: "+ p.menuNPCID);
//         System.out.println("select: "+ select);
        int tl;
        switch (p.menuNPCID) {

            case 13:
                if (p.menuID == 1) {
                    if (select == 0) {
                        p.openBox();
                    }
                }
                break;

            default: {
                Service.gI().sendTB(p.session, 0, "Chức Năng Đang Được Cập Nhật " + idNPC, 0);
                break;
            }

            //   Service.getInstance().serverMessage(p.session,"ID NPC " + b1);
        }
        m.cleanup();
    }

    public void openUINpc(Player p, Message m) throws IOException {
        short idnpc = m.reader().readShort();//idnpc
        int avatar;
        m.cleanup();
        p.menuID = -1;
        p.menuNPCID = idnpc;
        avatar = NpcAvatar(p, idnpc);
        m = new Message(33);

        if (p.menuNPCID == 52 && p.map.id == 0) {
            doMenuArray(p, idnpc, "Ò Ò sự Kiện Đua chơi chơi á mà\blụm x99 cần sa rồi đưa đây cho anh anh cho quà\bHoặc đem đủ 99kg thịt lợn qua đây\bĐổi quà Bình dân và Quà Sử thi để lấy hòm ulatr nhé ", new String[]{"Quà Bình Dân", "Quả Sử Thi", "Quà U là TR", "đóng"});
            return;
        } else if (p.menuNPCID == 73) {
            doMenuArray(p, idnpc, "Đổi password", new String[]{"OK", "Từ chối"});
            return;
        } else if (p.menuNPCID == 72 && p.map.id == 160) {
            doMenuArray(p, idnpc, "Chào mừng đến với cửa hàng của ta, sử dụng 10 huy chương đồng và 1000 ngọc để đổi những món đồ giá trị với chỉ số ngẫu nhiên lên tới 30% (Thu thập huy chương đồng bằng cách tiêu diệt Black Goku hoặc Cooler)", new String[]{"OK", "Từ chối"});
            return;
        } else if (p.menuNPCID == 71 && p.map.id == 161) {
            if (p.taskId == (short) 31) {
                if (p.crrTask.index == (byte) 5 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "Cậu sẽ đưa tôi về chỗ Bardock thật sao?");
                    return;
                }
            } else if (p.taskId == (short) 32) {
                if (p.crrTask.index == (byte) 2 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                    Item thuocMo = ItemSell.getItemNotSell(1016);
                    Item _thuocMo = new Item(thuocMo);
                    p.addItemToBag(_thuocMo);
                    Service.gI().updateItemBag(p);
                    p.sendAddchatYellow("Bạn nhận được Thuốc mỡ Ipana");
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "Cậu đã chạm trán tên đó rồi sao?");
                    return;
                }
            }
            Service.chatNPC(p, (short) p.menuNPCID, "Đi đu đưa đi...");
            return;
        } else if (p.menuNPCID == 70 && p.map.id == 160) { //BARDOCK
            if (p.taskId == (short) 31) {
                if (p.crrTask.index == (byte) 2 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "Tôi tên là Bardock, người Xayda\bHành tinh của tôi vừa bị Fide phá hủy\bKhông biết tại sao tôi thoát chết...\bvà xuất hiện tại nơi này nữa\bTôi đang bị thương, cậu hãy giúp tôi hạ đám lính ngoài kia");
                    return;
                } else if (p.crrTask.index == (byte) 4 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "Cảm ơn cậu\bGiờ nhờ cậu đi tìm nhóc Berry về giúp tôi\bcó thể cậu nhóc loanh quanh ở Bờ Rừng Nguyên Thủy");
                    return;
                } else if (p.crrTask.index == (byte) 6 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "Mơn cậu lần nữa\bHiện tại trong hang không còn gì để ăn\bCậu hãy giúp tôi tìm một ít lương thực");
                    return;
                } else if (p.crrTask.index == (byte) 7 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                    for (byte i = 0; i < p.ItemBag.length; i++) {
                        if (p.ItemBag[i] != null && p.ItemBag[i].template.id == 993 && (p.ItemBag[i].quantity == 99)) {
                            p.ItemBag[i] = null;
                            TaskService.gI().updateCountTask(p);
                            Service.gI().updateItemBag(p);
                            Service.chatNPC(p, (short) p.menuNPCID, "Mơn cậu thêm lần nữa\bVới số lương thực này tôi sẽ sớm bình phục\bNgoài kia bọn lính đang ức hiếp cư dân hành tinh này\bMong cậu có thể ra sức lần nữa để cứu họ");
                            return;
                        }
                    }
                    Service.chatNPC(p, (short) p.menuNPCID, "Cậu thu thập 99 giỏ thức ăn để dự trữ");
                    return;
                } else if (p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1)) {
                    if (p.power < (p.getPowerLimit() * 1000000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    p.sendAddchatYellow("Bạn vừa được thưởng " + Util.powerToString(p.crrTask.bonus) + " sức mạnh");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    return;
                }
            }
            Service.chatNPC(p, (short) p.menuNPCID, "Sáng ra suối, tối vào hang...");
            return;
        } else if (p.menuNPCID == 66 && p.map.id == 5) { //noi banh
            doMenuArray(p, idnpc, "Thu thập bánh chưng để nấu bánh\bĐổi Pháo hoa cần 1 trong 4 chữ Chúc,Mừng,Năm,Mới đủ 23 chữ", new String[]{"Tiến hành nấu", "Đổi pháo hoa", "Bxh Sự Kiện", "đóng"});
            return;
        } else if (p.menuNPCID == 67 && p.map.id == 0) { //MR POPO
            doMenuArray(p, idnpc, "Thượng đế vừa phát hiện 1 loại khí đang âm thầm\nhủy diệt mọi mầm sống trên Trái Đất,\nnó được gọi là Destron Gas.\nTa sẽ đưa các cậu đến nơi ấy, các cậu sẵn sàng chưa?", new String[]{"Thông tin\nChi tiết", "Top 100\nBang hội", "Thành tích\nBang", "OK", "Từ chối"});
            return;
        } else if (p.menuNPCID == 61) {//DOI YARDART
            doMenuArray(p, idnpc, "Hãy cố gắng tập luyện\nThu thập 9999 bí kiếp để đổi trang phục Yardrat nhé", new String[]{"Nhận thưởng", "OK"});
            return;
        } else if (p.menuNPCID == 60 && (p.map.id == 80 || p.map.id == 131)) { //GOKU NUI KHI VANG
            doMenuArray(p, idnpc, "Ta mới hạ Fide, nhưng nó đã kịp đào 1 cái lỗ\nHành tinh này sắp nổ tinh rồi\nMau lượn thôi", new String[]{"Chuẩn"});
            return;
        } else if (p.menuNPCID == 56) {
            if (p.map.id == 154) {
                doMenuArray(p, idnpc, "Mặc set Hủy Diệt và kiếm đủ 9999 mảnh trang bị ở Hành tinh ngục tù\nTa sẽ giúp ngươi nâng cấp trang bị thiên sứ.\nPhục vụ tốt ta có thể cho trang bị mạnh mẽ hơn đến 15%", new String[]{"Đồng ý", "Từ chối"});
            }
            return;
        } else if (p.menuNPCID == 55) { //THAN HUY DIET BILL
            if (p.map.id == 48) {
                doMenuArray(p, idnpc, "Không muốn hành tinh này bị hủy diệt thì mang 99 phần đồ ăn tới đây,\nta sẽ cho một món đồ Hủy Diệt.\nPhục vụ tốt ta có thể cho trang bị mạnh mẽ hơn đến 15%", new String[]{"Đồng ý", "Từ chối"});
            } else if (p.map.id == 154) {
                doMenuArray(p, idnpc, "...", new String[]{"Về\nThánh Địa\nKaio", "Từ chối"});
            }
            return;
        } else if (p.menuNPCID == 54) { //LY TIEU NUONG
            if (p.map.id == 5) {
                doMenuArray(p, idnpc, "Mua thịt hay cỏ thì ghé qua shop chị nhé\b ngoài ra chị còn bán ít rương quà bên trong thú vị lắm đấy", new String[]{"Thực Phẩm", "Shop\nRương","Bảng xếp hạng\n Sự kiện"});
                return;
            }
        } else if (p.menuNPCID == 53) { //TAPION
            if (p.map.id == 19) {
                doMenuArray(p, idnpc, "Ác quỷ truyền thuyết Hirudegarn\nđã thoát khỏi phong ấn ngàn năm\nHãy giúp tôi chế ngự nó", new String[]{"OK", "Từ chối"});
            } else if (p.map.id == 126) {
                doMenuArray(p, idnpc, "Ngươi muốn bỏ chạy sao?", new String[]{"OK", "Từ chối"});
            }
            return;
        } else if (p.menuNPCID == 74) { //Toribot
            if (p.map.id == 5) {
                doMenuArray(p,idnpc,"Chào mừng đến với cửa hàng của ta",new String[]{"OK","Từ chối"});
            }
            return;
        } else if (p.menuNPCID == 49) { //DUONG TANG
            if (p.map.id == 0) {
                doMenuArray(p, idnpc, "A mi phò phò, thí chủ hãy giúp giải cứu đồ đệ của bần tăng đang bị\nphong ấn tại ngũ hành sơn.", new String[]{"Đồng ý", "Từ chối", "Nhận thưởng"});
            } else if (p.map.id == 123) {
                doMenuArray(p, idnpc, "Thí chủ muốn trở về sao ?", new String[]{"Đồng ý", "Từ chối"});
            } else if (p.map.id == 122) {
                doMenuArray(p, idnpc, "A mi phò phò, thí chủ hãy thu thập bùa 'giải khai phong ấn', mỗi chữ 10 cái.", new String[]{"Giải\nPhong ấn", "Về\nLàng Aru", "Top\nHoa quả"});
            }
            return;
        } else if (p.menuNPCID == 47 && p.map.id == 153) { //NGUU MA VUONG
            doMenuArray(p, idnpc, "Á đù chú em muốn đến Bình Duơng up Mảnh vỡ và Mảnh hồn à?\nBình Dương, Anh sẽ đưa chú đến đó ?", new String[]{"OK", "Đóng"});
            return;
        } else if (p.menuNPCID == 46) { //BABIDAY
            if (p.map.id == 114 || p.map.id == 115 || p.map.id == 117 || p.map.id == 118 || p.map.id == 119 || p.map.id == 120) {
                if (p.cPk == (byte) 11) {
                    doMenuArray(p, idnpc, "Bọn Kaio do con nhóc Ôsin cầm đầu đã có mặt tại đây...Hãy chuẩn bị 'Tiếp\nkhách' nhé!", new String[]{"Hướng\ndẫn\nthêm", "Giải trừ\nphép thuật\n1 ngọc", "Xuống\nTầng dưới", "Về nhà"});
                } else {
                    doMenuArray(p, idnpc, "Haha...", new String[]{"OK"});
                }
            }
            return;
        } else if (p.menuNPCID == 44) { //OSSIN
            if (p.taskId == (short) 30) {
                if (p.crrTask.index == (byte) 0 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "Cậu hãy chuẩn bị để đi cùng chúng tôi");
                    return;
                } else if (p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1)) {
                    if (p.power < (p.getPowerLimit() * 100000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    p.sendAddchatYellow("Bạn vừa được thưởng " + Util.powerToString(p.crrTask.bonus) + " sức mạnh");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    return;
                }
            }
            if (p.map.id == 50) {
                doMenuArray(p, idnpc, "Ta có thể giúp gì ngươi?", new String[]{"Đến Kaio", "Đến\nhành tinh\nBill", "Từ chối"});
            } else if (p.map.id == 154) {
                doMenuArray(p, idnpc, "Ta có thể giúp gì ngươi?", new String[]{"Đến\nhành tinh\nNgục tù", "Từ chối"});
            } else if (p.map.id == 155) {
                doMenuArray(p, idnpc, "Ta có thể giúp gì ngươi?", new String[]{"Đến\nhành tinh\nBill", "Từ chối"});
            } else if (p.map.id == 52) {
                doMenuArray(p, idnpc, "Bây giờ tôi sẽ bí mật...\nđuổi theo 2 tên đồ tể...\nQuý vị nào muốn đi theo thì xin mời!", new String[]{"OK", "Từ chối"});
            } else if (p.map.id == 114 || p.map.id == 115 || p.map.id == 117 || p.map.id == 118 || p.map.id == 119 || p.map.id == 120) {
                if (p.cPk == (byte) 10) {
                    doMenuArray(p, idnpc, "Đừng vội xem thường Babiđây, ngay đến cha hắn là thần ma đạo sĩ\nBibiđây khi còn sống cũng phải sợ hắn đấy!", new String[]{"Hướng\ndẫn\nthêm", "Giải trừ\nphép thuật\n1 ngọc", "Xuống\nTầng dưới", "Về nhà"});
                } else {
                    doMenuArray(p, idnpc, "Haha...", new String[]{"OK"});
                }
            }
            return;
        } else if (p.menuNPCID == 41) { //TRUNG THU
            if (p.map.id == 14) {
                doMenuArray(p, idnpc, "Trung thu đến rồi, xin chào!", new String[]{"Cửa hàng"});
            }
            return;
        } else if ((p.menuNPCID <= 36 && p.menuNPCID >= 30) && (p.map.id >= 85 && p.map.id <= 91)) { //NGOC RONG SAO DEN
            doMenuArray(p, idnpc, "Ta có thể giúp gì cho người?", new String[]{"Phù hộ", "Từ chối"});
            return;
        } else if (p.menuNPCID == 29 && (p.map.id == 24 || p.map.id == 25 || p.map.id == 26)) { //NGOC RONG SAO DEN
            if (p.indexNRSD.size() > 0) {
                doMenuArray(p, idnpc, "Đường đến ngọc rồng sao đen đã mở, ngươi có muốn tham gia không?", new String[]{"Hướng\ndẫn\nthêm", "Nhận thưởng", "Tham gia", "Từ chối"});
            } else {
                doMenuArray(p, idnpc, "Đường đến ngọc rồng sao đen đã mở, ngươi có muốn tham gia không?", new String[]{"Hướng\ndẫn\nthêm", "Tham gia", "Từ chối"});
            }
            return;
        } else if (p.menuNPCID == 23 && p.map.id == 52) {  //GHI DANH DAI HOI VO THUAT
            //Đã hết hạn đăng ký thi đấu, xin vui lòng chờ đến giải sau vào lúc 15h
            if (DaiHoiManager.gI().openDHVT) {
                String nameDH = DaiHoiManager.gI().nameRoundDHVT();
                doMenuArray(p, idnpc, "Chào mừng bạn đến với đại hội võ thuật\bGiải " + nameDH + " đang có " + DaiHoiManager.gI().lstIDPlayers.size() + " người đăng ký thi đấu", new String[]{"Thông tin\bChi tiết", "Đăng ký", "Giải\nSiêu Hạng", "Đại Hội\nVõ Thuật\nLần thứ\n23"});
            } else {
                doMenuArray(p, idnpc, "Đã hết hạn đăng ký thi đấu, xin vui lòng chờ đến giải sau", new String[]{"Thông tin\bChi tiết", "OK", "Giải\nSiêu Hạng", "Đại Hội\nVõ Thuật\nLần thứ\n23"});
            }
            return;
        } else if (p.menuNPCID == 21) {
            if (p.map.id == 5) {
                doMenuArray(p, idnpc, "Ngươi tìm ta có việc gì?", new String[]{"Ép sao trang\nbị", "Pha lê hóa\ntrang bị"});
            } else if (p.map.id == 42 || p.map.id == 43 || p.map.id == 44) {
                doMenuArray(p, idnpc, "Ngươi tìm ta có việc gì?", new String[]{"Cửa hàng\nBùa", "Nâng cấp Vật\n phẩm", "Làm phép\nNhập đá", "Nhập\nNgọc Rồng", "Nâng cấp\nBông tai\nPorata", "Mở chỉ số\nBông tai\nPorata cấp 2"});
            }
            return;
        } else if (p.menuNPCID == 20 && p.map.id == 48) {  //THAN KAIO
            doMenuArray(p, idnpc, "Con muốn quay về sao?", new String[]{"Về Thần Điện", "Thánh Địa\nKaio", "Từ Chối"});
            return;
        } else if (p.menuNPCID == 19 && p.map.id == 45) {
            doMenuArray(p, idnpc, "Con đã mạnh hơn ta, ta sẽ chỉ đường cho con đến Kaio để gặp thần Vũ\nTrụ Phương Bắc\nNgài là thần cai quản vũ trụ này, hãy theo ngài ấy học võ công", new String[]{"Đến Kaio", "Quay ngọc\nMay mắn"});
            return;
        } else if (p.menuNPCID == 10 && p.map.id == 24) {
            doMenuArray(p, idnpc, "Tàu Vũ Trụ của tôi có thể đưa cậu đến hành tinh khác trong 3 giây. Cậu muốn đi đâu", new String[]{"Đến\nNamếc", "Đến\nXayda", "Từ Chối"});
            return;
        } else if (p.menuNPCID == 11 && p.map.id == 25) {
            doMenuArray(p, idnpc, "Tàu Vũ Trụ Namếc tuy cũ nhưng tốc độ không hề kém bất kỳ loại tầu nào khác. Cậu muốn đi đâu?", new String[]{"Đến\nTrái Đất", "Đến\nXayda", "Từ Chối"});
            return;
        } else if (p.menuNPCID == 12) {
            if (p.map.id == 19) {
                if (p.taskId == (short) 21 && p.crrTask.index == (byte) 0) {
                    doMenuArray(p, idnpc, "Đội quân Fide đang ở Thung Lũng Nappa, ta sẽ đưa ngươi đến đó", new String[]{"Đến\nCold", "Đến\nNappa", "Từ Chối", "Đến chỗ\nKuku"});
                } else if (p.taskId == (short) 21 && p.crrTask.index == (byte) 1) {
                    doMenuArray(p, idnpc, "Đội quân Fide đang ở Thung Lũng Nappa, ta sẽ đưa ngươi đến đó", new String[]{"Đến\nCold", "Đến\nNappa", "Từ Chối", "Đến chỗ\nMập Đầu Đinh"});
                } else if (p.taskId == (short) 21 && p.crrTask.index == (byte) 2) {
                    doMenuArray(p, idnpc, "Đội quân Fide đang ở Thung Lũng Nappa, ta sẽ đưa ngươi đến đó", new String[]{"Đến\nCold", "Đến\nNappa", "Từ Chối", "Đến chỗ\nRambo"});
                } else {
                    doMenuArray(p, idnpc, "Đội quân Fide đang ở Thung Lũng Nappa, ta sẽ đưa ngươi đến đó", new String[]{"Đến\nCold", "Đến\nNappa", "Từ Chối"});
                }
            } else if (p.map.id == 68) {
                doMenuArray(p, idnpc, "Ngươi muốn bỏ chạy ư?", new String[]{"Đồng ý", "Từ Chối"});
            } else if (p.map.id == 26) {
                doMenuArray(p, idnpc, "Tàu vũ trụ Xayda sử dụng công nghệ mới nhất, có thể đưa ngươi đi bất kỳ đâu, chỉ cần trả tiền là được", new String[]{"Đến\nTrái Đất", "Đến\nNamếc", "Từ Chối"});
            }
            return;
        } else if (p.menuNPCID == 43 && p.map.id == 50) { //To su mo gioi han suc manh
            doMenuArray(p, idnpc, "Muốn chơi đồ cho khỏe kh con?", new String[]{"Bản thân", "Đệ tử", "Từ Chối"});
            return;
        } else if (p.menuNPCID == 42 && p.map.id == 43) { //Quoc vuong mo gioi han suc manh
            doMenuArray(p, idnpc, "Con muốn nâng giới hạn sức mạnh cho bản thân hay đệ tử?", new String[]{"Bản thân", "Đệ tử", "Từ Chối"});
            return;
        } else if (p.menuNPCID == 38 && (p.map.id == 27 || p.map.id == 102)) { //TRUNKS SANG TUONG LAI
            if (p.taskId >= (short) 23) {
                if (p.taskId == (short) 24 && p.crrTask.index == (byte) 1 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "Cháu đến từ tương lai và cháu cần các chú giúp. Đây là thuốc trợ tim cho Quy Lão, không lâu nữa Quy Lão sẽ bị bệnh.");
                    return;
                }
                if (p.map.id != 102) {
                    doMenuArray(p, idnpc, "Chào chú cháu có thể giúp gì?", new String[]{"Đi đến Tương lai", "Từ Chối"});
                    return;
                } else {
                    doMenuArray(p, idnpc, "Chào chú cháu có thể giúp gì?", new String[]{"Quay về\nQuá khứ", "Từ Chối"});
                    return;
                }
            } else {
                p.sendAddchatYellow("Phải hoàn thành nhiệm vụ trước khi tới đây");
                Service.gI().buyDone(p);
            }
            return;
        } else if (p.menuNPCID == 37 && p.map.id == 102) { //BUNMA TUONG LAI
            if (p.taskId == (short) 24 && p.crrTask.index == (byte) 3 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                TaskService.gI().updateCountTask(p);
                Service.chatNPC(p, (short) p.menuNPCID, "Cảm ơn cậu đã đến đây, chúng tôi đang gặp rắc rối lớn. Hãy giúp chúng tôi tiêu diệt lũ Xên gần đây");
                return;
            } else {
                if (p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1)) {
                    if (p.power < (p.getPowerLimit() * 1000000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    p.sendAddchatYellow("Bạn vừa được thưởng " + Util.powerToString(p.crrTask.bonus) + " sức mạnh");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    return;
                }
            }
            doMenuArray(p, idnpc, "Cảm ơn bạn đã đến đây để giúp chúng tôi", new String[]{"Kể Chuyện", "Cửa hàng"});
            return;
        } else if (p.menuNPCID == 16 && (p.map.id == 24 || p.map.id == 25 || p.map.id == 26)) {
            TabItemShop[] test = Shop.getTabShop(16, p.gender).toArray(new TabItemShop[0]);
            GameScr.UIshop(p, test);
//                doMenuArray(p,idnpc,Text.get(0, 1),new String[]{"Cửa Hàng"});
            return;
        } else if (p.menuNPCID == 25 && p.map.id == 27) {
            if (p.clan != null) {
                if (p.clan.openDoanhTrai) {
                    doMenuArray(p, idnpc, "Doanh trại Độc Nhãn đang được mở,\nngươi có chắc chắn muốn vào trại Độc Nhãn (còn " + (30 - (int) ((System.currentTimeMillis() - p.clan.topen) / 60000)) + " phút)", new String[]{"OK", "Từ Chối"});
                } else {
                    doMenuArray(p, idnpc, "Ngươi có chắc chắn muốn vào trại Độc Nhãn", new String[]{"OK", "Từ Chối"});
                }
            } else {
                doMenuArray(p, idnpc, "Vui lòng gia nhập bang hội", new String[]{"OK", "Từ Chối"});
            }
            return;
        } else if (p.menuNPCID == 1 || p.menuNPCID == 0 || p.menuNPCID == 2) {
            if ((p.menuNPCID == 0 && p.map.id == 21) || (p.menuNPCID == 1 && p.map.id == 23) || (p.menuNPCID == 2 && p.map.id == 22)) {
                if (p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1)) {
                    if (p.power < (p.getPowerLimit() * 1000000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    p.sendAddchatYellow("Bạn vừa được thưởng " + Util.powerToString(p.crrTask.bonus) + " sức mạnh");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    if (p.taskId == (short) 4) {
                        TaskService.gI().setupNextNewTask(p, (byte) 7);
                    } else {
                        TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    }
                    return;
                } else if (p.taskId == (short) 8 && p.crrTask.index == (byte) 2) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "Con đã nghe về rồng thần chưa, thứ có thể thực hiện được điều ước. Gần đây có tháp Karin, con hãy đến đó xem xét tình hình");
                    return;
                } else if (p.taskId == (short) 24 && p.crrTask.index == (byte) 0) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "OK, ta biết rồi. Bây giờ con hãy đi tìm vị khách lạ đó");
                    return;
                }
                if (p.hasTrungMabu) {
                    doMenuArray(p, idnpc, Text.get(0, 0), new String[]{"GiftCode", "Nhận Ngọc", "Đệ Tử\nMabư"});
                } else {
                    doMenuArray(p, idnpc, Text.get(0, 0), new String[]{"GiftCode", "Nhận Ngọc"});
                }
            }
            return;
        } else if (p.menuNPCID == 39 && p.map.id == 5) {
            doMenuArray(p, idnpc, Text.get(0, 1), new String[]{"Cửa Hàng"});
            return;
        } else if (p.menuNPCID == 9 && p.map.id == 14) {
            if (p.gender == 2) {
                if (p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID && p.crrTask.index == (byte) 2 && p.taskId == (short) 7) {
                    Service.chatNPC(p, (short) p.menuNPCID, "Cảm ơn ngươi đã cứu ta. Ta sẽ sẵn sàng phục vụ nếu ngươi cần mua vật dụng");
                    TaskService.gI().updateCountTask(p);
                    return;
                }
                doMenuArray(p, idnpc, Text.get(0, 1), new String[]{"Cửa Hàng"});
            } else {
                Service.gI().sendTB(p.session, 0, "Ta chỉ bán đồ cho hành tinh Xayda", 0);
            }
            return;
        } else if (p.menuNPCID == 7 && p.map.id == 0) {
            if (p.gender == 0) {
                if (p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID && p.crrTask.index == (byte) 2 && p.taskId == (short) 7) {
                    Service.chatNPC(p, (short) p.menuNPCID, "Cảm ơn ngươi đã cứu ta. Ta sẽ sẵn sàng phục vụ nếu ngươi cần mua vật dụng");
                    TaskService.gI().updateCountTask(p);
                    return;
                }
                doMenuArray(p, idnpc, Text.get(0, 1), new String[]{"Cửa Hàng"});
            } else {
                Service.gI().sendTB(p.session, 0, "Ta chỉ bán đồ cho hành tinh Trái đất", 0);
            }
            return;
        } else if (p.menuNPCID == 8 && p.map.id == 7) {
            if (p.imgNRSD == (byte) 53) {
                if (p.map.id == 7) {
                    doMenuArray(p, idnpc, "Ồ, ngọc rồng namếc, bạn thật là may mắn\nnếu tìm đủ 7 viên sẽ được Rồng Thiêng Namếc ban cho điều ước", new String[]{"Hướng\ndẫn\nGọi Rồng", "Gọi rồng", "Từ chối"});
                }
            } else {
                if (p.gender == 1) {
                    if (p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID && p.crrTask.index == (byte) 2 && p.taskId == (short) 7) {
                        Service.chatNPC(p, (short) p.menuNPCID, "Cảm ơn ngươi đã cứu ta. Ta sẽ sẵn sàng phục vụ nếu ngươi cần mua vật dụng");
                        TaskService.gI().updateCountTask(p);
                        return;
                    }
                    doMenuArray(p, idnpc, Text.get(0, 1), new String[]{"Cửa Hàng"});

                } else {
                    Service.gI().sendTB(p.session, 0, "Ta chỉ bán đồ cho hành tinh Namếc", 0);
                }
            }
            return;
        } else if (p.menuNPCID == 13 && p.map.id == 5) { //QUY LAO
            if (p.taskId == (short) 12) {
//                if(p.crrTask.index == (byte)(p.crrTask.countSub - (byte)1) && p.crrTask.subtasks[(byte)(p.crrTask.countSub - (byte)1)] == (byte)p.menuNPCID && p.clan != null
//                && p.clan.members.size() >= 5 && p.gender == (byte)0) {
                if (p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1) && p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.gender == (byte) 0) {
                    if (p.power < (p.getPowerLimit() * 1000000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    p.sendAddchatYellow("Bạn vừa được thưởng " + Util.powerToString(p.crrTask.bonus) + " sức mạnh");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    return;
                }
            } else if (p.taskId == (short) 24) {
                if (p.crrTask.index == (byte) 2 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "Ta không biết chuyện gì sắp xảy ra nhưng cảm ơn con");
                    return;
                }
            } else {
                if (((p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1))
                        || (p.taskId == (short) 9 && p.crrTask.index == (byte) 0)) && p.gender == (byte) 0) {
                    if (p.power < (p.getPowerLimit() * 1000000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    p.sendAddchatYellow("Bạn vừa được thưởng " + Util.powerToString(p.crrTask.bonus) + " sức mạnh");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    if (p.taskId == (short) 9) {
                        TaskService.gI().setupNextNewTask(p, (byte) 12);
                    } else if (p.taskId == (short) 17) {
                        TaskService.gI().setupNextNewTask(p, (byte) 20);
                    } else {
                        TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    }
                    return;
                }
            }
//            doMenuArray(p,idnpc,"Ta có thể giúp gì cho con",new String[]{"Nói chuyện","Tính năng","Đá nâng cấp","Bang hội"});
            doMenuArray(p, idnpc, "Ta có thể giúp gì cho con", new String[]{"Nói chuyện", "BXH", "Bang hội", "Quỹ nạp"});
            return;
        } else if (p.menuNPCID == 14 && p.map.id == 13) { //TRUONG LAO GURU
            if (p.taskId == (short) 12) {
//                if(p.crrTask.index == (byte)(p.crrTask.countSub - (byte)1) && p.crrTask.subtasks[(byte)(p.crrTask.countSub - (byte)1)] == (byte)p.menuNPCID && p.clan != null
//                && p.clan.members.size() >= 5 && p.gender == (byte)1) {
                if (p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1) && p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.gender == (byte) 1) {
                    if (p.power < (p.getPowerLimit() * 1000000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    p.sendAddchatYellow("Bạn vừa được thưởng " + Util.powerToString(p.crrTask.bonus) + " sức mạnh");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    return;
                }
            } else {
                if (((p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1))
                        || (p.taskId == (short) 9 && p.crrTask.index == (byte) 0)) && p.gender == (byte) 1) {
                    if (p.power < (p.getPowerLimit() * 1000000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    p.sendAddchatYellow("Bạn vừa được thưởng " + Util.powerToString(p.crrTask.bonus) + " sức mạnh");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    if (p.taskId == (short) 9) {
                        TaskService.gI().setupNextNewTask(p, (byte) 12);
                    } else if (p.taskId == (short) 17) {
                        TaskService.gI().setupNextNewTask(p, (byte) 20);
                    } else {
                        TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    }
                    return;
                }
            }
            Service.gI().sendTB(p.session, 0, "Chức Năng Đang Được Cập Nhật " + idnpc, 0);
            return;
        } else if (p.menuNPCID == 15 && p.map.id == 20) { //VUA VEGETA
            if (p.taskId == (short) 12) {
//                if(p.crrTask.index == (byte)(p.crrTask.countSub - (byte)1) && p.crrTask.subtasks[(byte)(p.crrTask.countSub - (byte)1)] == (byte)p.menuNPCID && p.clan != null
//                && p.clan.members.size() >= 5 && p.gender == (byte)2) {
                if (p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1) && p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.gender == (byte) 2) {
                    if (p.power < (p.getPowerLimit() * 1000000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    p.sendAddchatYellow("Bạn vừa được thưởng " + Util.powerToString(p.crrTask.bonus) + " sức mạnh");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    return;
                }
            } else {
                if (((p.crrTask.subtasks[(byte) (p.crrTask.countSub - (byte) 1)] == (byte) p.menuNPCID && p.crrTask.index == (byte) (p.crrTask.countSub - (byte) 1))
                        || (p.taskId == (short) 9 && p.crrTask.index == (byte) 0)) && p.gender == (byte) 2) {
                    if (p.power < (p.getPowerLimit() * 1000000000L)) {
                        p.tiemNang += p.crrTask.bonus;
                        p.power += p.crrTask.bonus;
                    }
                    p.UpdateSMTN((byte) 2, p.crrTask.bonus);
                    p.sendAddchatYellow("Bạn vừa được thưởng " + Util.powerToString(p.crrTask.bonus) + " sức mạnh");
                    Service.chatNPC(p, (short) p.menuNPCID, TaskManager.gI().textChatTASK[p.taskId]);
                    if (p.taskId == (short) 9) {
                        TaskService.gI().setupNextNewTask(p, (byte) 12);
                    } else if (p.taskId == (short) 17) {
                        TaskService.gI().setupNextNewTask(p, (byte) 20);
                    } else {
                        TaskService.gI().setupNextNewTask(p, (byte) (p.taskId + (byte) 1));
                    }
                    return;
                }
            }
            Service.gI().sendTB(p.session, 0, "Chức Năng Đang Được Cập Nhật " + idnpc, 0);
            return;
        } else if (p.menuNPCID == 18 && p.map.id == 46) { //THAN MEO
            if (p.taskId == (short) 29 && p.crrTask.index == (byte) 0 && p.crrTask.subtasks[p.crrTask.index] == (byte) p.menuNPCID) {
                if (p.damGoc >= 10000) {
                    TaskService.gI().updateCountTask(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "Con hãy tiếp tục đến tương lai và thu thập Capsule kì bí");
                    return;
                } else {
                    Service.gI().buyDone(p);
                    Service.chatNPC(p, (short) p.menuNPCID, "Con hãy nâng sức đánh gốc lên 10K rồi quay lại gặp ta");
                    return;
                }
            }
            return;
        } else if (p.menuNPCID == 4 && (p.map.id == 21 || p.map.id == 22 || p.map.id == 23)) {
            if (p.upMagicTree) {
                doMenuArray(p, idnpc, "Cây đậu thần", new String[]{"Nâng cấp\nnhanh " + ngocUpMagicTree(p.levelTree) + "\nngọc", "Hủy nâng\ncấp hồi " + Util.powerToString((long) (goldUpMagicTree(p.levelTree) / 2)) + "\nvàng"});
            } else {
                if (p.levelTree >= (byte) 10) {
                    if (p.currentBean < p.maxBean) {
                        doMenuArray(p, idnpc, "Cây đậu thần", new String[]{"Thu hoạch", "Thu đậu nhanh\n" + ngocThuDauThan(p.levelTree) + " ngọc"});
                    } else {
                        doMenuArray(p, idnpc, "Cây đậu thần", new String[]{"Thu hoạch"});
                    }
                } else {
                    if (p.currentBean < p.maxBean) {
                        doMenuArray(p, idnpc, "Cây đậu thần", new String[]{"Thu hoạch", "Nâng cấp\n" + timeStringUpMagicTree(p.levelTree) + " " + Util.powerToString((long) goldUpMagicTree(p.levelTree)) + "\nvàng", "Thu đậu nhanh\n" + ngocThuDauThan(p.levelTree) + " ngọc"});
                    } else {
                        doMenuArray(p, idnpc, "Cây đậu thần", new String[]{"Thu hoạch", "Nâng cấp\n" + timeStringUpMagicTree(p.levelTree) + " " + Util.powerToString((long) goldUpMagicTree(p.levelTree)) + "\nvàng"});
                    }
                }
            }
            return;
        } else if (p.menuNPCID == 3) {
            p.openBox();
            return;
        } else {
            Service.gI().sendTB(p.session, 0, "Chức Năng Đang Được Cập Nhật " + idnpc, 0);
        }
        m.writer().flush();
        p.session.sendMessage(m);
        m.cleanup();
    }

    public int NpcAvatar(Player p, int npcID) {

        for (int i = 0; i < p.getPlace().map.template.npcs.length; i++) {
            if (p.getPlace().map.template.npcs[i].tempId == npcID) {
                return p.getPlace().map.template.npcs[i].avartar;
            }

        }
        return -1;
    }

    public int vangMoNoiTai(byte count) {
        if (count == 0) {
            return 0;
        } else if (count == 1) {
            return 10;
        } else if (count == 2) {
            return 20;
        } else if (count == 3) {
            return 40;
        } else if (count == 4) {
            return 80;
        } else if (count == 5) {
            return 160;
        } else if (count == 6) {
            return 320;
        } else if (count == 7) {
            return 640;
        } else {
            return 1280;
        }
//        return 1280;
    }

    //vang nang cap dau than
    public int goldUpMagicTree(byte level) {
        if (level == (byte) 1) {
            return 5000;
        } else if (level == (byte) 2) {
            return 10000;
        } else if (level == (byte) 3) {
            return 100000;
        } else if (level == (byte) 4) {
            return 1000000;
        } else if (level == (byte) 5) {
            return 10000000;
        } else if (level == (byte) 6) {
            return 20000000;
        } else if (level == (byte) 7) {
            return 50000000;
        } else if (level == (byte) 8) {
            return 100000000;
        } else if (level == (byte) 9) {
            return 300000000;
        }
        return 300000000;
    }

    //ngoc nang cap NHANH CAY DAU THAN
    public int ngocUpMagicTree(byte level) {
        if (level == (byte) 1) {
            return 10;
        } else if (level == (byte) 2) {
            return 100;
        } else if (level == (byte) 3) {
            return 1000;
        } else if (level == (byte) 4) {
            return 3000;
        } else if (level == (byte) 5) {
            return 5000;
        } else if (level == (byte) 6) {
            return 7000;
        } else if (level == (byte) 7) {
            return 8000;
        } else if (level == (byte) 8) {
            return 9000;
        } else if (level == (byte) 9) {
            return 10000;
        }
        return 10000;
    }

    //NGOC THU HOACH NHANH DAU THAN
    public int ngocThuDauThan(byte level) {
        if (level == (byte) 1) {
            return 2;
        } else if (level == (byte) 2) {
            return 5;
        } else if (level == (byte) 3) {
            return 8;
        } else if (level == (byte) 4) {
            return 11;
        } else if (level == (byte) 5) {
            return 14;
        } else if (level == (byte) 6) {
            return 17;
        } else if (level == (byte) 7) {
            return 20;
        } else if (level == (byte) 8) {
            return 23;
        } else if (level == (byte) 9) {
            return 26;
        }
        return 26;
    }

    //TIME NANG CAP DAU THAN
    public int timeUpMagicTree(byte level) {
        if (level == (byte) 1) {
            return 600;
        } else if (level == (byte) 2) {
            return 6000;
        } else if (level == (byte) 3) {
            return 58920;
        } else if (level == (byte) 4) {
            return 597600;
        } else if (level == (byte) 5) {
            return 1202400;
        } else if (level == (byte) 6) {
            return 2592000;
        } else if (level == (byte) 7) {
            return 4752000;
        } else if (level == (byte) 8) {
            return 5961600;
        } else if (level == (byte) 9) {
            return 8640000;
        }
        return 8640000;
    }

    //TIME STRING UP DAU THAN
    public String timeStringUpMagicTree(byte level) {
        if (level == (byte) 1) {
            return "10m";
        } else if (level == (byte) 2) {
            return "1h40m";
        } else if (level == (byte) 3) {
            return "16h22m";
        } else if (level == (byte) 4) {
            return "6d22h";
        } else if (level == (byte) 5) {
            return "13d22h";
        } else if (level == (byte) 6) {
            return "30d";
        } else if (level == (byte) 7) {
            return "55d";
        } else if (level == (byte) 8) {
            return "69d";
        } else if (level == (byte) 9) {
            return "100d";
        }
        return "100d";
    }
}
