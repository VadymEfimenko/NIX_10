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
values (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'SOLO', 'cent');
insert into musicians
values (3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORCHESTRA', 'orchestra');
insert into musicians
values (4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'QUARTET', 'brothers');
insert into musicians
values (5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'QUINTET', 'The Strokes');

insert into releases
values (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'считается, что пиковые yo la tengo - это группа периода 93-00,
но для меня даже лучшие их вещи той поры в лучшим случае равны обычным, средним вещам периода 00 - 06.
мне нравится чуть не полуторачасовой инструментальный саундтрек "The Sounds of the Sounds of Science",
сорокаминутный сингл-кавер на сан ра и, конечно, мне нравится часовой лаунж-джаз "Summer Sun"',
        'https://e.snmc.io/i/600/w/7e2f222e77f44f417537ca48ba1b4d41/1604353/yo-la-tengo-summer-sun-Cover-Art.jpg',
        'Summer Sun', 200, 10, 'Long-playing', true, 1);

insert into releases
values (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        'как бы не бодрились нынешние поклонники строукс - второй альбом группы разочаровывающе бледен на фоне первого. хот тэйк: это не результат творческого бессилия, а всего лишь ошибка менеджмента - материал "Room on Fire" надо было просто выпустить в другой форме. по-прежнему нелепо малоизвестный бутлег выступления строукс через месяц после выхода альбома делает абсолютно все песни с него лучше и заодно превращает избранные поп-шедевры с дебюта в стадионную классику. ретро-группа, строукс обязаны были выпустить этот материал ровно в таком виде - у каждой группы классик-рока были официальные концертные альбомы, у многих (cheap trick) они были общепризнанно лучше студийного материала. с годами я почти совсем перестал слушать классик-рок, а вот "Live at Alexandra Palace" включаю регулярно.',
        'https://lastfm.freetls.fastly.net/i/u/500x500/5cd92d6e685feaeaffcda19f8509cc8f.jpg',
        'Live at Alexandra Palace', 199, 4, 'record album', true, 5);

insert into distributor_release values(1, 1);
insert into distributor_release values(2, 2);

