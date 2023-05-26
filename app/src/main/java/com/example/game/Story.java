package com.example.game;

import android.view.View;

public class Story {
    StartGame gs;
    String nextScene1, nextScene2, nextScene3;
    String choice1 = "", choice2 = "", choice3 = "";
    String text = "";
    Integer health = 100, petrol = 0;
    Boolean knife = false, tools = false, rope = false, gun = false;
    Boolean  pass = false, man = false;
    public Story(StartGame gs) {
        this.gs = gs;
    }

    public void selectScene(String scene, String choice) {
        if (health <= 0) {
            health = 0;
            text = "Вы получили слишком много ранений и погибли";
            death(text);
        } else {
            switch (scene) {
                case "bedroom":
                    bedroom();
                    break;
                case "kitchen":
                    kitchen();
                    break;
                case "basement":
                    basement(choice);
                    break;
                case "collect":
                    checkItems();
                    break;
                case "items":
                    getItems(choice);
                    break;
                case "street":
                    goAway();
                    break;
                case "house":
                    goToHouse();
                    break;
                case "help":
                    helpWoman();
                    break;
                case "search":
                    checkHouse(choice);
                    break;
                case "garage":
                    garage();
                    break;
                case "table":
                    table();
                    break;
                case "checkPetrol":
                    checkPetrol(choice);
                    break;
                case "gasStation":
                    gasStation();
                    break;
                case "highway":
                    road(choice);
                    break;
                case "around":
                    anotherWay();
                    break;
                case "car":
                    getCar(choice);
                    break;
                case "man":
                    meetMan();
                    break;
                case "bandits":
                    attack();
                    break;
                case "direction":
                    direction(choice);
                    break;
                case "camp":
                    camp();
                    break;
                case "settlement":
                    settlement();
                    break;
                case "offroad":
                    offRoad();
                    break;
                case "afoot":
                    goAlone(choice);
                    break;
                case "meeting":
                    oldMan();
                    break;
                case "chat":
                    campMan(choice);
                    break;
                case "restart":
                    gs.restart();
                    break;
            }
        }
    }

    public void setVisible() {
        gs.button1.setVisibility(View.VISIBLE);
        gs.button2.setVisibility(View.VISIBLE);
        gs.button3.setVisibility(View.VISIBLE);
    }

    public void setInvisible() {
        gs.button1.setVisibility(View.INVISIBLE);
        gs.button2.setVisibility(View.INVISIBLE);
        gs.button3.setVisibility(View.INVISIBLE);
    }

    public void startingPoint() {
        gs.bg.setImageResource(R.drawable.bedroom);
        gs.textScene.setText("Выборы игрока влияют на ход сюжета и могут привести к преждевременному завершению игры.\n" +
                "Предметы в инвентаре могут давать дополнительный выбор.\n" +
                "Следите за уровнем здоровьем, достижение нуля приведёт к завершению игры.");

        setInvisible();
        gs.button1.setVisibility(View.VISIBLE);
        gs.button1.setText("Начать");
        nextScene1 = "bedroom";
    }

    public void bedroom() {
        gs.bg.setImageResource(R.drawable.bedroom);
        gs.textScene.setText("Вы просыпаетесь в полночь от шума военных самолетов.\n" +
                "В горле пересохло, нужно спуститься на кухню за водой и заодно прочитать новости");

        setInvisible();
        gs.button1.setVisibility(View.VISIBLE);
        gs.button1.setText("Спуститься вниз");
        nextScene1 = "kitchen";
    }

    public void kitchen() {
        gs.bg.setImageResource(R.drawable.kitchen);
        gs.textScene.setText("Проходя мимо подвала, вы слышите подозрительный скрежет и решаете его проверить." +
                "\nЧто возьмете с собой?");
        setVisible();
        gs.button1.setText("Взять стакан воды");
        gs.button2.setText("Взять телефон");
        gs.button3.setText("Взять нож");

        nextScene1 = "basement";
        nextScene2 = "basement";
        nextScene3 = "basement";

        choice1 = "вода";
        choice2 = "телефон";
        choice3 = "нож";
    }

    public void basement(String choice) {
        gs.bg.setImageResource(R.drawable.basement);
        setInvisible();
        gs.button2.setVisibility(View.VISIBLE);
        gs.button2.setText("Пойти на кухню");

        if (choice == "телефон") {
            gs.textScene.setText("Спустившись в подвал, вы обнаруживаете человека в углу.\n" +
                    "Подойдя ближе, замечаете у него рану и предлагаете помощь, но незнакомец бросается на вас.\n" +
                    "Вдруг телефон начинает звонить и от неожиданности выпадает из рук, что отвлекает нападавшего.\n" +
                    "Выбегая из подвала, вы спотыкаетесь и сильно ударяете ногу, но успеваете запереть подвал.");
            health -= 20;
            choice2 = "телефон";
            nextScene2 = "collect";
        } else if (choice == "нож") {
            gs.textScene.setText("Спустившись в подвал, вы обнаруживаете человека в углу.\n" +
                    "Подойдя ближе, замечаете у него рану на ноге и окликаете, предлагая помощь.\n" +
                    "Незнакомец бросается в вашу сторону, но вам удается отбиться ножом и убежать наверх.");
            choice2 = "нож";
            nextScene2 = "collect";
        } else if (choice == "вода") {
            text = "Спустившись в подвал, вы обнаруживаете человека в углу.\n" +
                    "Подойдя ближе, замечаете у него рану на ноге и окликаете, предлагая помощь.\n" +
                    "Незнакомец бросается в вашу сторону, вам нечем защититься.\n" +
                    "Вы погибли";
            death(text);
        }
    }

    public void checkItems() {
        gs.bg.setImageResource(R.drawable.kitchen);
        if (health >= 100) {
            gs.textScene.setText("По радио объявляют экстренные новости, сообщается о новом вирусе, передающемся через кровь.\n" +
                    "Всех людей просят ехать в военный лагерь за чертой города, нужно скорее собрать вещи и вызжать.\n" +
                    "Впустят всех, кто успеет приехать до захода солнца.\n" +
                    "У вас достаточно сил, чтобы обойти весь дом и взять все необходимое\n" +
                    "Что возьмете?");
            setInvisible();
            gs.button2.setVisibility(View.VISIBLE);
            gs.button2.setText("Нож, инструменты и трос");
            choice2 = "ниф";
            nextScene2 = "items";
        } else {
            gs.textScene.setText("По радио объявляют экстренные новости, сообщается о новом вирусе, передающемся через кровь.\n" +
                    "Всех людей просят ехать в военный лагерь за чертой города, нужно скорее собрать вещи и вызжать.\n" +
                    "Впустят всех, кто успеет приехать до захода солнца.\n" +
                    "Вы повредили ногу, поэтому нужно взять только самое необходимое.\n" +
                    "Что возьмете?");
            setVisible();
            gs.button1.setText("Нож и инструменты");
            gs.button2.setText("Трос и нож");
            gs.button3.setText("Инструменты и трос");
            choice1 = "ни";
            choice2 = "нф";
            choice3 = "иф";
            nextScene1 = "items";
            nextScene2 = "items";
            nextScene3 = "items";
        }
    }

    public void getItems(String choice) {
        if (choice == "ни") {
            knife = true;
            tools = true;
        } else if (choice == "нф") {
            knife = true;
            rope = true;
        } else if (choice == "иф") {
            tools = true;
            rope = true;
        } else if (choice == "ниф") {
            knife = true;
            tools = true;
            rope = true;
        }
        goAway();
    }

    public void goAway() {
        setVisible();
        gs.bg.setImageResource(R.drawable.street);
        gs.textScene.setText("Взяв вещи, вы выбегаете на улицу. Из соседнего дома слышен женский крик.\n" +
                "Как поступите?");
        gs.button1.setText("Проверить дом");
        gs.button2.setText("Скорее уезжать");
        gs.button3.setVisibility(View.INVISIBLE);

        nextScene1 = "house";
        nextScene2 = "checkPetrol";
    }

    public void checkHouse(String choice) {
        gs.bg.setImageResource(R.drawable.house);
        setVisible();
        if (choice == "petrol" & petrol == 0) {
            petrol += 1;
        } else if (choice == "gun") {
            gun = true;
        }
        if (health >= 80) {
            gs.textScene.setText("К счастью, у вас достаточно сил, чтобы обыскать весь дом.");
        } else {
            gs.textScene.setText("У вас не так много сил, " +
                    "поэтому придется осмотреть только одно место.");
        }
        gs.button1.setText("Осмотреть гараж");
        gs.button2.setText("Осмотреть комнату");
        gs.button3.setText("Уйти");
        nextScene1 = "garage";
        nextScene2 = "table";
        nextScene3 = "checkPetrol";
        choice1 = "";
        choice2 = "";
        choice3 = "";
    }

    public void garage() {
        gs.bg.setImageResource(R.drawable.garage);
        setInvisible();
        gs.button2.setVisibility(View.VISIBLE);

        if (petrol == 0) {
            gs.button1.setVisibility(View.VISIBLE);
            gs.textScene.setText("В гараже вы обнаруживаете канистру с бензином");
            gs.button1.setText("Взять канистру");
        } else {
            gs.textScene.setText("Здесь больше нет ничего полезного");
        }
        if (health >= 80) {
            gs.button2.setText("Вернуться");
            nextScene2= "search";
        } else {
            gs.button2.setText("Уйти");
            nextScene2= "checkPetrol";
        }

        nextScene1 = "search";
        choice1 = "petrol";
        choice2 = "";
    }

    public void table() {
        gs.bg.setImageResource(R.drawable.table);
        setInvisible();
        gs.button2.setVisibility(View.VISIBLE);

        if (!gun) {
            gs.button1.setVisibility(View.VISIBLE);
            gs.textScene.setText("В письменном столе вы обнаруживаете ящик с двойным дном, осмотрев его, находите пистолет");
            gs.button1.setText("Взять пистолет");
        } else {
            gs.textScene.setText("Здесь больше нет ничего полезного");
        }
        if (health >= 80) {
            gs.button2.setText("Вернуться");
            nextScene2= "search";
        } else {
            gs.button2.setText("Уйти");
            nextScene2= "checkPetrol";
        }
        nextScene1 = "search";
        choice1 = "gun";
        choice2 = "";
    }

    public void goToHouse() {
        gs.bg.setImageResource(R.drawable.room);
        gs.textScene.setText("Вы заходите в дом и поднимаетесь на второй этаж.\n" +
                "В комнате вы видите женщину, которая отбивается от зараженного.\n" +
                "Что сделать?");
        gs.button1.setText("Спасти женщину");
        gs.button2.setText("Обыскать дом");
        gs.button3.setVisibility(View.INVISIBLE);

        nextScene1 = "help";
        nextScene2 = "search";
    }

    public void helpWoman() {
        setInvisible();
        gs.button1.setVisibility(View.VISIBLE);
        gs.button1.setText("Забрать пропуск");
        if (knife) {
            gs.textScene.setText("Вы подбегаете и наносите удар ножом сзади. Женщина благодарно смотрит на вас, и вы замечаете укус на ее шее.\n" +
                    "Она разрешает вам взять все необходимое, протягивает пропуск и, не успевая ничего сказать, умирает.");
        } else {
            health -= 10;
            gs.textScene.setText("Вы хватаете стул и наносите удар сзади. Не удержав равновесие, неудачно приземляетесь на руку.\n" +
                    "Женщина благодарит, но оказывается укушена. Она разрешает вам взять все необходимое, передает пропуск и, не успевая ничего сказать, умирает.");
        }
        nextScene1 = "search";
        pass = true;
    }

    public void checkPetrol(String choice) {
        setVisible();
        gs.button3.setVisibility(View.INVISIBLE);
        gs.bg.setImageResource(R.drawable.street);
        gs.textScene.setText("Вы садитесь в машину и обнаруживаете, что бензина осталось совсем немного.\n" +
                "Как поступить?");

        if (choice == "канистра" & petrol == 0) {
            petrol += 1;
        } else if (choice == "пистолет") {
            gun = true;
        }

        if (petrol >= 1) {
            gs.button3.setVisibility(View.VISIBLE);
            gs.button3.setText("Заправить машину");
            nextScene3 = "highway";
            choice3 = "заправить";
        }
        gs.button1.setText("Поехать на заправку");
        gs.button2.setText("Поехать в военный лагерь");

        nextScene1 = "gasStation";
        nextScene2 = "highway";
        choice1 = "";
        choice2 = "";
    }

    public void gasStation() {
        setInvisible();
        gs.bg.setImageResource(R.drawable.station);
        gs.textScene.setText("Вы доезжаете до заправки, к счастью, вокруг ни души. Вы хватаете канистру с бензином.");
        petrol += 1;

        gs.button1.setVisibility(View.VISIBLE);
        gs.button1.setText("Заправить машину");
        nextScene1 = "highway";
        choice1 = "заправить";
    }

    public void road(String choice) {
        if (choice != "заправить") {
            text = "Бензина хватает лишь на половину пути, вам приходится выйти и идти пешком.\n" +
                    "Шум машины привлек внимание зараженных, и из-за угла выбегает толпа.\n" +
                    "Вы погибли";
            death(text);
        } else {
            petrol -= 1;
            setVisible();
            gs.bg.setImageResource(R.drawable.traffic);
            gs.textScene.setText("Вы как можно скорее уезжаете, но перед выездом на шоссе пробка, все бегут из города.\n" +
                    "Что сделать?");
            gs.button1.setText("Съехать на бездорожье");
            gs.button2.setText("Поехать другой дорогой");
            gs.button3.setVisibility(View.INVISIBLE);

            nextScene1 = "offroad";
            nextScene2 = "around";
        }
    }

    public void anotherWay() {
        setInvisible();
        gs.bg.setImageResource(R.drawable.car);
        health -= 20;
        if (gun) {
            gs.textScene.setText("Дорога обошлась без происшествий, но на последнем повороте под колеса бросается зараженный.\n" +
                    "Вы теряете управление, и машина врезается в столб.\n" +
                    "Выбежав из машины, вы сталкиваетесь с причиной аварии, но успевате выстрелить.\n" +
                    "Можно двигаться дальше, если найти инструменты.\n" +
                    "Что сделать?");
        } else if (knife) {
            health -= 10;
            gs.textScene.setText("Дорога обошлась без происшествий, но на последнем повороте под колеса бросается зараженный.\n" +
                    "Вы теряете управление, и машина врезается в столб.\n" +
                    "Выбежав из машины, вы сталкиваетесь с причиной аварии, но успевате достать нож и спасаетесь.\n" +
                    "Можно двигаться дальше, если найти инструменты.\n" +
                    "Что сделать?");
        } else {
            text = "Дорога обошлась без происшествий, но на последнем повороте под колеса бросается зараженный.\n" +
                    "Вы теряете управление, и машина врезается в столб.\n" +
                    "Выбежав из машины, вы сталкиваетесь с причиной аварии. У вас нет оружия, вы не смогли спастись и погибли.";
            death(text);
        }

        gs.button1.setText("Осмотреться");
        gs.button1.setVisibility(View.VISIBLE);
        if (tools) {
            gs.button2.setVisibility(View.VISIBLE);
            gs.button2.setText("Починить машину");
            choice2 = "инструменты";
        }
        nextScene1 = "man";
        nextScene2 = "car";
    }

    public void meetMan() {
        setVisible();
        gs.button3.setVisibility(View.INVISIBLE);
        gs.bg.setImageResource(R.drawable.mancar);
        gs.textScene.setText("Вы идете вдоль дороги и вдалеке замечаете мужчину, стоящего возле машины и окликаете его.\n" +
                "Мужчина испуганно смотрит на вас.\n" +
                "Как поступить?");
        gs.button1.setText("Предложить объединиться");
        gs.button2.setText("Украсть машину");
        if (petrol >= 1 & !tools) {
            gs.button3.setText("Обменять бензин на инструменты");
            gs.button3.setVisibility(View.VISIBLE);
        }

        nextScene1 = "car";
        nextScene2 = "car";
        nextScene3 = "car";

        choice1 = "объединение";
        choice2 = "кража";
        choice3 = "обмен";
    }

    public void getCar(String choice) {
        if (choice == "обмен") {
            gs.bg.setImageResource(R.drawable.mancar);
            gs.textScene.setText("Вы договариваетесь обменять бензин на инструменты и чините машину, теперь можно ехать");
            tools = true;
            petrol -= 1;
        } else if (choice == "инструменты") {
            gs.bg.setImageResource(R.drawable.mancar);
            gs.textScene.setText("Вы чините машину, теперь можно ехать");
        } else if (choice == "кража"){
            gs.bg.setImageResource(R.drawable.mancar);
            if (gun) {
                gs.textScene.setText("Вы угрожаете мужчине пистолетом, он просит оставить его в покое взамен на машину");
            } else if (knife) {
                gs.textScene.setText("Вы угрожаете мужчине ножом, он набрасывается на вас и бьет. Ранив мужчину, он просит оставить его в покое взамен на машину");
                health -= 10;
            }
        } else if (choice == "объединение") {
            man = true;
            gs.textScene.setText("Вы предлагаете мужчине объединиться и поехать вместе дальше, он соглашается");
        }
        setInvisible();
        gs.button1.setVisibility(View.VISIBLE);
        gs.button1.setText("Отправиться в путь");
        nextScene1 = "bandits";
    }

    public void attack() {
        gs.bg.setImageResource(R.drawable.highway);
        gs.textScene.setText("Перед выездом на шоссе дорогу преграждает другая машина, из которой начинают стрелять.\n" +
                "Как поступить?");
        setVisible();
        gs.button3.setVisibility(View.INVISIBLE);
        gs.button1.setText("Спрятаться в машине");
        gs.button2.setText("Предложить договориться");
        nextScene1 = "direction";
        nextScene2 = "direction";
        choice1 = "спрятаться";
        choice2 = "договориться";
        if (gun) {
            gs.button3.setVisibility(View.VISIBLE);
            gs.button3.setText("Выстрелить");
            nextScene3 = "direction";
            choice3 = "выстрел";
        }
    }

    public void direction(String choice) {
        setInvisible();
        if (choice == "выстрел") {
            gs.bg.setImageResource(R.drawable.countryside);
            if (man) {
                gs.textScene.setText("Вы стреляете в ответ и отбиваетесь.\n" +
                        "По дороге мужчина рассказывает вам, что направлялся в общину, в которой будет сохранен прежний уклад жизни.\n" +
                        "По его словам, военный лагерь представляет собой тюрьму для выживших, а местная элита отправится на отдельный остров.\n" +
                        "Куда поехать?");
                gs.button1.setVisibility(View.VISIBLE);
                gs.button2.setVisibility(View.VISIBLE);
                gs.button1.setText("Поехать в военный лагерь");
                gs.button2.setText("Поехать в общину");
                nextScene1 = "camp";
                nextScene2 = "settlement";
            } else {
                gs.textScene.setText("Вы стреляете в ответ и отбиваетесь.");
                gs.button1.setVisibility(View.VISIBLE);
                gs.button1.setText("Поехать в военный лагерь");
                nextScene1 = "camp";
            }
        } else if (choice == "спрятаться") {
            gs.bg.setImageResource(R.drawable.highway);
            if (man) {
                gs.textScene.setText("Вы прячетесь в машине, но мужчина выбегает из нее и бежит в другую сторону.\n" +
                        "Вы пытаетесь остановить мужчину, но его застреливают. Вы пользуетесь тем, что бандиты отвлеклись и уезжаете.");
                gs.button1.setVisibility(View.VISIBLE);
                gs.button1.setText("Поехать в военный лагерь");
                nextScene1 = "camp";
                man = false;
            } else {
                gs.textScene.setText("Вы прячетесь в машине и ждете. Один из бандитов подбирается совсем близко, стреляет и попадает вам в плечо. \n" +
                        "Вдруг вдалеке слышите выстрелы, из-за чего бандиты отвлеклись.\n" +
                        "Воспользовавшись моментом, вы уезжаете.");
                gs.button1.setVisibility(View.VISIBLE);
                gs.button1.setText("Поехать в военный лагерь");
                nextScene1 = "camp";
                health -= 30;
                if (health <= 0) {health = 0;}
            }
        } else if (choice == "договориться") {
            text = "Вы выходите из машины и предлагаете договориться.\n" +
                    "Бандиты предлагают отдать им все и, расценив ваше возмущенное лицо как отказ, стреляют.\n" +
                    "Вы погибли.";
            death(text);
        }

    }

    public void offRoad() {
        setVisible();
        gs.button3.setVisibility(View.INVISIBLE);
        gs.bg.setImageResource(R.drawable.offroad);
        gs.textScene.setText("Вы съезжаете на бездорожье, в темноте практически не видно дорогу, и машина увязает в грязи.\n" +
                "Вы выходите и слышите рычание со стороны леса.\n" +
                "Что сделать?");
        gs.button1.setText("Оставить машину");
        gs.button2.setText("Вытолкнуть машину");
        if (rope) {
            gs.button3.setVisibility(View.VISIBLE);
            gs.button3.setText("Вытянуть машину тросом");
            nextScene3 = "afoot";
            choice3 = "rope";
        }
        nextScene1 = "afoot";
        nextScene2 = "afoot";
        choice1 = "уйти";
        choice2 = "вытолкнуть";
    }

    public void goAlone(String choice) {
        setInvisible();
        if (choice == "rope") {
            gs.button1.setVisibility(View.VISIBLE);
            gs.textScene.setText("Вы обвязываете тросом дерево и вытягиваете машину из грязи.");
            gs.button1.setText("Поехать дальше");
            nextScene1 = "camp";
        } else if (choice == "уйти") {
            gs.button1.setVisibility(View.VISIBLE);
            gs.textScene.setText("В темноте ничего не видно, а сил, чтобы вытолкнуть машину, не хватит.\n" +
                    "Вам пришлось оставить машину и пойти пешком, к счастью, военный лагерь совсем близко.");
            gs.button1.setText("Пойти дальше");
            nextScene1 = "meeting";
        } else if (choice == "вытолкнуть") {
            text = "В темноте ничего не видно, поэтому вы безуспешно толкаете машину.\n" +
                    "В этот момент к вам подкрадывается зараженный и нападает. Все произошло слишком неожиданно.\n" +
                    "Вы погибли.";
            death(text);
        }
    }

    public void oldMan() {
        gs.bg.setImageResource(R.drawable.goalone);
        gs.textScene.setText("Вы вышли к дороге и пошли пешком.\n" +
                "Вдруг останавливается пикап, доброжелательный пожилой мужчина предлагает вас подвезти.\n" +
                "В пути мужчина задает много личных вопросов.\n" +
                "Как отреагировать?");
        setVisible();
        gs.button1.setText("Ответить вежливо");
        gs.button2.setText("Проигнорировать");
        gs.button3.setText("Нагрубить мужчине");
        nextScene1 = "chat";
        nextScene2 = "chat";
        nextScene3 = "chat";
        choice1 = "+";
        choice2 = "+-";
        choice3 = "-";
    }

    public void camp() {
        if (pass) {
            gs.bg.setImageResource(R.drawable.island);
            gs.textScene.setText("Военный лагерь огорожен высокими стенами, на входе всех проверяют военные.\n" +
                                "Вас проводят в отдельную комнату, где сидит вся местная элита.\n" +
                                "Вас вместе с остальными отправят на безопасный остров, где ученые будут изучать природу вируса и искать лекарство.");
        } else {
            if (health >= 20) {
                gs.bg.setImageResource(R.drawable.post);
                gs.textScene.setText("Военный лагерь огорожен высокими стенами, на входе всех проверяют военные.\n" +
                                    "Людей просят выйти из машин и осматривают на наличие укусов.\n" +
                                    "Вас проводят внутрь, где перед толпой выступает мужчина в военной форме.\n" +
                                    "Он рассказывает вам новые правила: подъем в 6, отбой в 9 и работа на благо общества.");
            } else {
                gs.bg.setImageResource(R.drawable.field);
                gs.textScene.setText("Военный лагерь огорожен высокими стенами, на входе всех проверяют военные.\n" +
                                    "У вас замечают большое количество ран и не пропускают.\n" +
                                    "Дальше придется выживать самому.");
            }
        }
        setInvisible();
        gs.button1.setVisibility(View.VISIBLE);
        gs.button1.setText("Начать заново");
        nextScene1 = "restart";
    }

    public void campMan(String choice) {
        setInvisible();
        if (choice == "+-") {
            gs.bg.setImageResource(R.drawable.post);
            gs.textScene.setText("Подъезжая к военному лагерю, мужчина рассказывает о своей военной карьере.\n" +
                    "Досмотрщик приветливо встречает водителя и спрашивает о вас.\n" +
                    "Вы были не очень вежливы, поэтому мужчина сообщает, что просто подвез вас.");
            gs.button1.setVisibility(View.VISIBLE);
            gs.button1.setText("Пройти осмотр");
            nextScene1 = "camp";
        } else if (choice == "+") {
            gs.bg.setImageResource(R.drawable.island);
            gs.textScene.setText("Подъезжая к военному лагерю, мужчина рассказывает о своей военной карьере.\n" +
                            "Досмотрщик приветливо встречает водителя и спрашивает о вас.\n" +
                            "Вы были вежливы, поэтому мужчина представляет вас как своего племянника.\n" +
                            "Вас проводят в отдельную комнату, где сидит вся местная элита.\n" +
                            "Вас вместе с остальными отправят на безопасный остров, где ученые будут изучать природу вируса и искать лекарство.");
            gs.button1.setVisibility(View.VISIBLE);
            gs.button1.setText("Начать заново");
            nextScene1 = "restart";
        } else if (choice == "-"){
            gs.bg.setImageResource(R.drawable.goalone);
            text = "Оскорбленный мужчина останавливает машину и высаживает вас.\n" +
                    "Вдалеке виднеются огни военного лагеря, из кустов на вас неожиданно набрасывается зараженный.\n" +
                    "Вы погибли.";
            death(text);
        }
    }

    public void settlement() {
        gs.bg.setImageResource(R.drawable.settle);
        gs.textScene.setText("Вы добираетесь до огороженного коттеджного поселка. На въезде стоят люди.\n" +
                "Они осматривают машину и проверяют людей на вирус. После чего доброжелательно пропускают.\n" +
                "Глава общины предоставляет вам комнату и посвящает в местные правила.\n" +
                "Всем нужно будет выбрать деятельность и вместе строить новую жизнь.");
        setInvisible();
        gs.button1.setVisibility(View.VISIBLE);
        gs.button1.setText("Начать заново");
        nextScene1 = "restart";
    }

    public void death(String text) {
        setInvisible();
        gs.textScene.setText(text);
        gs.button1.setText("Начать заново");
        gs.button1.setVisibility(View.VISIBLE);
        nextScene1 = "restart";
    }
}
