insert into distributors
values (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Диск', 'MPEG-1/2/2.5 Layer 3', 'universal', 'universal.com');
insert into distributors
values (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Кассета', 'MPEG-4 Part 14', 'company', 'company.com');
insert into distributors
values (3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Стриминг', 'MPEG-1/2/2.5 Layer 3', 'music', 'music.com');
insert into distributors
values (4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Стриминг', 'MPEG-4 Part 14', 'mp3', 'mp3.com');
insert into distributors
values (5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Пластинка', 'Free Lossless Audio Codec', 'muzfor', 'muzfor.com');


insert into musicians
values (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'TRIO', 'Yo La Tengo');
insert into musicians
values (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SOLO', 'Sally Shapiro');
insert into musicians
values (3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORCHESTRA', 'The Hold Steady');
insert into musicians
values (4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'QUARTET', 'The Flaming Lips');
insert into musicians
values (5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'QUINTET', 'The Strokes');

insert into releases
values (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'считается, что пиковые yo la tengo - это группа периода 93-00,
но для меня даже лучшие их вещи той поры в лучшим случае равны обычным, средним вещам периода 00 - 06.
мне нравится чуть не полуторачасовой инструментальный саундтрек "The Sounds of the Sounds of Science",
сорокаминутный сингл-кавер на сан ра и, конечно, мне нравится часовой лаунж-джаз "Summer Sun"',
        'https://e.snmc.io/i/600/w/7e2f222e77f44f417537ca48ba1b4d41/1604353/yo-la-tengo-summer-sun-Cover-Art.jpg',
        'Summer Sun', 200, 10, 'ALBUM', true, 1);

insert into releases
values (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        'как бы не бодрились нынешние поклонники строукс - второй альбом группы разочаровывающе бледен на фоне первого.
        хот тэйк: это не результат творческого бессилия,
        а всего лишь ошибка менеджмента - материал "Room on Fire" надо было просто выпустить в другой форме. по-прежнему
нелепо малоизвестный бутлег выступления строукс через месяц после выхода альбома делает абсолютно все песни с него лучше
и заодно превращает избранные поп-шедевры с дебюта в стадионную классику. ретро-группа, строукс обязаны были выпустить
этот материал ровно в таком виде - у каждой группы классик-рока были официальные концертные альбомы, у многих
(cheap trick) они были общепризнанно лучше студийного материала. с годами я почти совсем перестал слушать классик-рок,
а вот "Live at Alexandra Palace" включаю регулярно.',
        'https://lastfm.freetls.fastly.net/i/u/500x500/5cd92d6e685feaeaffcda19f8509cc8f.jpg',
        'Live at Alexandra Palace', 199, 4, 'ALBUM', true, 5);
insert into releases
values (3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ледяная хрустящая музыка первого морозца, звук ностальгии по первой
любви (вероятно из-за звука в духе юры шатунова). на этом месте мог быть первый альбом певицы максим, на котором, конечно,
есть несколько превосходных песен, но и аранжировки шалят и полальбома - мура. у салли нет "нежности" и "знаешь ли ты",
но зато все остальное - идеально.',
        'https://vinyla.com/files/products/d6dc23e1da3e4b15b9526eb88e543731.800x800.png?f4e6311a28176b76ff3a1e0284934f5c',
        'Disco Romance', 100, 3, 'ALBUM', true, 2);

insert into releases
values (4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'репетиция перед дискографией ариэля пинка 2010-х: теплый, нежный и
полистилистический лоу-фай-рок, сделанный в студии мастерами звукозаписи. песни слабее пинковских, но зато нет и его
всегдашней ершистости, а только томная нега бабьего лета отлитая в звуке.',
        'https://e.snmc.io/i/600/w/fffd6f18e0f8f558a84b0fd3b4e9e5b0/1810430/the-hold-steady-boys-and-girls-in-america-Cover-Art.jpg',
        'Boys and Girls in America', 150, 4, 'ALBUM', true, 3);

insert into releases
values (5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'грубый, жирно записанный и вырвиглазно сведенный гаражный рок, который
сам собой складывается в расплывающуюся психоделическую кашу - самая точная, какую я вообще знаю, стилизация ранней
психоделии, при этом контр-интуитивная (ну вроде бы логично психоделию делать мягкой и сладкой). местами нудноватый
альбом (эмбиент-куски), но лучшие песни, где-то полчаса чистого времени, прямо идеальны.',
        'https://e.snmc.io/i/600/w/5fa1e69fc109dd802e340361a0a94b32/2901936/the-flaming-lips-embryonic-Cover-Art.jpg',
        'Embryonic', 130, 3, 'ALBUM', true, 4);


insert into distributor_release
values (1, 1);
insert into distributor_release
values (2, 2);
insert into distributor_release
values (3, 3);
insert into distributor_release
values (4,4);
insert into distributor_release
values (5,5);

