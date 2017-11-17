-- Table: public.rooms

-- DROP TABLE public.rooms;

CREATE TABLE public.rooms
(
  name character varying(20),
  numitems integer,
  maxitems integer,
  description character varying(100),
  room_id integer PRIMARY KEY,
  item1 character varying(20) references items(name),
  item2 character varying(20) references items(name),
  item3 character varying(20) references items(name),
  item4 character varying(20) references items(name)

);

-- Table: public.items

-- DROP TABLE public.items;

CREATE TABLE public.items
(
  name character varying(20) UNIQUE NOT NULL,
  description character varying(100),
  weight integer,
  item_id integer PRIMARY KEY
);

CREATE TABLE public.lockedrooms
(
  name character varying(20) NOT NULL,
  item1 character varying(20) references items(name),
  item2 character varying(20) references items(name),
  item3 character varying(20) references items(name),
  item4 character varying(20) references items(name),
  key character varying(20) references items(name),
  numitems integer,
  maxitems integer,
  description character varying(100),
  room_id integer PRIMARY KEY
);
  

CREATE TABLE public.player
(
  name character(20) PRIMARY KEY,
  inventory json[],
  numinven integer,
  maxitems integer,
  maxweight integer,
  room character(20),
  roomflag1 boolean,
  roomflag2 boolean,
  roomflag3 boolean,
  roomflag4 boolean
);

INSERT INTO public.Rooms VALUES ('center', 4, 9, 'The room you first found yourself in.', 1, 'red_key','blue_key','yellow_key', 'indigo_key');

INSERT INTO public.Rooms VALUES ('left',1, 9, 'The room left of the center room', 2, null, null, null, null);

INSERT INTO public.Rooms VALUES ('right', 1, 9, 'The room right of the center room', 3, null, null, null, null);

INSERT INTO public.Rooms VALUES ('up', 1, 9, 'The room that is above the center room', 4, null, null, null, null);

INSERT INTO public.Rooms VALUES ('down', 1, 9, 'The room below the center room', 5, null, null, null, null);

INSERT INTO public.Rooms VALUES ('upRight', 0, 9, 'The room at the top right of the map', 6, null, null, null, null);

INSERT INTO public.Rooms VALUES ('upLeft', 0, 9, 'The room at the top left of the map', 7, null, null, null, null);

INSERT INTO public.Rooms VALUES ('downRight', 0, 9, 'The room at the bottom right of the map', 8, null, null, null, null);

INSERT INTO public.Rooms VALUES ('downLeft', 0, 9, 'The room at the bottom left of the map', 9, null, null, null, null);

INSERT INTO public.Items VALUES('note_1', 'You need', 1 ,1);

INSERT INTO public.Items VALUES('note_2', 'to use', 1 ,2);

INSERT INTO public.Items VALUES('note_3', 'the command', 1 ,3);

INSERT INTO public.Items VALUES('note_4', 'win game', 1 ,4);

INSERT INTO public.Items VALUES('red_key', 'A red key, used to unlock the rightmost room.', 1 ,5);

INSERT INTO public.Items VALUES('blue_key', 'A blue key, used to unlock the leftmost room.', 1 ,6);

INSERT INTO public.Items VALUES('yellow_key', 'A yellow key, used to unlock the bottommost room.', 1 ,7);

INSERT INTO public.Items VALUES('indigo_key', 'A pretty key, used to unlock the topmost room.', 1 ,8);

INSERT INTO public.lockedrooms VALUES ('lockedleft', 'note_1', null, null, null, 'blue_key', 1, 9, 'The locked room to the left of the center room', 1);

INSERT INTO public.lockedrooms VALUES ('lockedRight', 'note_2', null, null, null, 'red_key', 1, 9, 'The locked room to the right of the map', 2);

INSERT INTO public.lockedrooms VALUES ('lockedUp', 'note_3', null, null, null, 'yellow_key', 1, 9, 'The locked room at  the top of the map', 3);

INSERT INTO public.lockedrooms VALUES ('lockedDown', 'note_4', null, null, null, 'indigo_key', 1, 9, 'The locked room at the bottom of the map', 4);