-- schema --------------------------------------------------------
-- |
-- V
DROP SCHEMA IF EXISTS projet CASCADE;
CREATE SCHEMA projet;
COMMENT ON SCHEMA projet IS 'Schema du projet BDR';

SET search_path TO projet;

create table "table"(
    numero smallserial not null,
    nbPlace int check ( nbPlace > 0 ),
    primary key (numero)
);

create table orientation (
    id smallserial,
    nom text,
    primary key (id)
);

create table utilisateur (
    pseudo varchar(20),
    email text not null unique,
    password text not null,
    orientation int,
    admin bool not null,
    primary key (pseudo),
    foreign key (orientation) references orientation(id)

);

create table evenement(
    id smallserial,
    nom text not null,
    date date not null,
    organisateur varchar(20) not null,
    primary key (id),
    foreign key (organisateur) references utilisateur(pseudo)
);

create table table_event(
    numeroTable int,
    numeroEvent int,
    primary key(numeroEvent, numeroTable),
    foreign key (numeroTable) references "table"(numero),
    foreign key (numeroEvent) references evenement(id)
);

create table type(
    id smallserial,
    nom text not null,
    primary key (id)
);

create table boissons(
    id smallserial,
    nom text not null,
    type int not null,
    quantitestock int not null check ( quantitestock >= 0 ),
    prixVente float not null check ( prixVente >= 0 ),
    prixAchat float not null check ( prixAchat >= 0 ),
    primary key (id),
    foreign key (type) references type(id)
);

create table reservation(
    numeroBoisson int,
    numeroEvent int,
    quantite int check(quantite > 0),
    primary key (numeroBoisson, numeroEvent),
    foreign key (numeroBoisson) references boissons(id),
    foreign key (numeroEvent) references evenement(id)
);

create table transaction(
    id smallserial,
    addition float check(addition >= 0),
    date timestamp with time zone default now() not null,
    by text not null ,
    primary key (id),
    foreign key (by) references utilisateur(pseudo)
);

create table boisson_transaction(
    numeroBoisson int,
    numeroTransaction int,
    quantite int not null check ( quantite > 0 ),
    primary key (numeroBoisson, numeroTransaction),
    foreign key (numeroBoisson) references boissons(id),
    foreign key (numeroTransaction) references transaction(id)
);

create table provenance(
    id smallserial,
    pays text not null,
    region text not null,
    brasserie text not null,
    primary key (id)
);

create table style(
    id smallserial,
    nom text,
    primary key (id)
);

create table alcool(
    id smallserial,
    pourcentage float not null check(pourcentage >= 0 and pourcentage <= 100.0),
    contenance float not null check(contenance > 0),
    noteMoyenne float,
    provenance int,
    style int,
    boisson int,
    primary key (id),
    foreign key (provenance) references provenance(id),
    foreign key (style) references style(id),
    foreign key (boisson) references boissons(id)
);

create table commentaire (
    id smallserial,
    date timestamp with time zone default now() not null,
    note int check( note >= 0 and note <= 5 ),
    comment text,
    commentBy text not null ,
    numeroBoisson int not null ,
    primary key (id),
    foreign key (commentBy) references utilisateur(pseudo),
    foreign key (numeroBoisson) references alcool(id)
);
-- import --------------------------------------------------------
-- |
-- V

-- Insertion des table dans la table table (lol)
insert into "table" values (1,2);
insert into "table" values (2,2);
insert into "table" values (3,2);
insert into "table" values (4,2);
insert into "table" values (5,4);
insert into "table" values (6,4);
insert into "table" values (7,4);
insert into "table" values (8,8);
insert into "table" values (9,8);
insert into "table" values (10,12);


insert into orientation (nom)
            values ('TIC');

insert into orientation (nom)
            values ('TIN');

insert into orientation (nom)
            values ('COMEM');

-- Insertion des utilisateurs dans la table utilisateurs
insert into utilisateur values ('xxx_DarkYohann_xxx','a@hotmail.com','mypassword',(SELECT id FROM orientation where nom = 'TIC'),true);
insert into utilisateur values ('xxx_DarkEnzo_xxx','b@hotmail.com','mypassword',(SELECT id FROM orientation where nom = 'TIC'),true);
insert into utilisateur values ('xxx_DarkMelvin_xxx','c@hotmail.com','mypassword',(SELECT id FROM orientation where nom = 'TIC'),true);
insert into utilisateur values ('Paul','d@hotmail.com','mypassword',(SELECT id FROM orientation where nom = 'TIN'),false);
insert into utilisateur values ('Marie','e@hotmail.com','mypassword',(SELECT id FROM orientation where nom = 'COMEM'),false);
insert into utilisateur values ('Frank','f@hotmail.com','mypassword',(SELECT id FROM orientation where nom = 'COMEM'),false);

-- Insertion des evenement dans la table evenement
insert into evenement (nom, date, organisateur) values ('Apero TIC','20201126','xxx_DarkYohann_xxx');
insert into evenement (nom, date, organisateur) values ('Apero TIN','20200926','xxx_DarkEnzo_xxx');
insert into evenement (nom, date, organisateur) values ('Apero COMEM','20200226','Marie');
insert into evenement (nom, date, organisateur) values ('Fondue TIC','20200526','xxx_DarkEnzo_xxx');

-- Insertion dans la table associative
insert into table_event values (1,(select id from evenement where nom = 'Apero TIC'));
insert into table_event values (10,(select id from evenement where nom = 'Fondue TIC'));

-- Insertion dans la table type
insert into type values (1,'Soft');
insert into type values (2,'Alcool');

-- Insertion des softs dans la table boisson
insert into boissons (nom, type, quantitestock, prixvente, prixachat) values ('Coca',1,1000,2,1);
insert into boissons (nom, type, quantitestock, prixvente, prixachat) values ('The froid',1,1000,2,1);
insert into boissons (nom, type, quantitestock, prixvente, prixachat) values ('Fanta',1,1000,2,1);
insert into boissons (nom, type, quantitestock, prixvente, prixachat) values ('Orangina',1,1000,2,1);
insert into boissons (nom, type, quantitestock, prixvente, prixachat) values ('RedBull',1,1000,4,3);
insert into boissons (nom, type, quantitestock, prixvente, prixachat) values ('Club Mate',1,1000,4,2);

-- Insertion des alcools dans la table boisson
insert into boissons (nom, type, quantitestock, prixvente, prixachat) values ('Boxer',2,1000,3,2);
insert into boissons (nom, type, quantitestock, prixvente, prixachat) values ('BFM',2,1000,3,2);
insert into boissons (nom, type, quantitestock, prixvente, prixachat) values ('Cuvee',2,1000,3,2);
insert into boissons (nom, type, quantitestock, prixvente, prixachat) values ('Feldschlosschen',2,1000,3,2);
insert into boissons (nom, type, quantitestock, prixvente, prixachat) values ('Prix Garanti',2,1000,3,2);
insert into boissons (nom, type, quantitestock, prixvente, prixachat) values ('Farmer',2,1000,3,2);
insert into boissons (nom, type, quantitestock, prixvente, prixachat) values ('Chimay',2,1000,3,2);
insert into boissons (nom, type, quantitestock, prixvente, prixachat) values ('Delirium',2,1000,3,2);

-- Insertion des reservations dans la table reservation
insert into reservation values ((select id from boissons where nom = 'Boxer'),(select id from evenement where nom = 'Apero TIC'),150);
insert into reservation values ((select id from boissons where nom = 'Coca'),(select id from evenement where nom = 'Apero TIC'),50);
insert into reservation values ((select id from boissons where nom = 'Farmer'),(select id from evenement where nom = 'Apero TIC'),500);
insert into reservation values ((select id from boissons where nom = 'The froid'),(select id from evenement where nom = 'Fondue TIC'),80);
insert into reservation values ((select id from boissons where nom = 'Delirium'),(select id from evenement where nom = 'Apero COMEM'),100);
insert into reservation values ((select id from boissons where nom = 'Club Mate'),(select id from evenement where nom = 'Apero COMEM'),8000);

-- Insertion des provenances dans la table provenance
insert into provenance (pays, region, brasserie) values ('Suisse','Yverdon','Boxer Brasserie');
insert into provenance (pays, region, brasserie) values ('Suisse','Jura','BFM Brasserie');
insert into provenance (pays, region, brasserie) values ('Suisse','Landi','Landie Brasserie');
insert into provenance (pays, region, brasserie) values ('Belgique','on c pa','Abbaye');
insert into provenance (pays, region, brasserie) values ('Allemagne','on c pa','Universal Brasserie');

-- Insertion des styles dans la table styles
insert into style (nom) values ('IPA');
insert into style (nom) values ('Blanche');
insert into style (nom) values ('Blonde');
insert into style (nom) values ('Ambree');

-- Insertion des alcools dans la table alcool
insert into alcool (pourcentage, contenance, notemoyenne, provenance, style, boisson)
            values (3.4,0.3,0,
                    (select id from provenance where brasserie = 'Boxer Brasserie'),
                    (select id from style where nom = 'Blonde'),
                    (select id from boissons where nom = 'Boxer'));
insert into alcool (pourcentage, contenance, notemoyenne, provenance, style, boisson)
            values (4.5,0.3,0,
                    (select id from provenance where brasserie = 'BFM Brasserie'),
                    (select id from style where nom = 'IPA'),
                    (select id from boissons where nom = 'BFM'));
insert into alcool (pourcentage, contenance, notemoyenne, provenance, style, boisson)
            values (3.4,0.5,0,
                    (select id from provenance where brasserie = 'Universal Brasserie'),
                    (select id from style where nom = 'Blanche'),
                    (select id from boissons where nom = 'Farmer'));

insert into alcool (pourcentage, contenance, notemoyenne, provenance, style, boisson)
            values (3.4,0.3,0,
                    (select id from provenance where brasserie = 'Universal Brasserie'),
                    (select id from style where nom = 'Blonde'),
                    (select id from boissons where nom = 'Cuvee'));

insert into alcool (pourcentage, contenance, notemoyenne, provenance, style, boisson)
            values (3.4,0.3,0,
                    (select id from provenance where brasserie = 'Universal Brasserie'),
                    (select id from style where nom = 'Blonde'),
                    (select id from boissons where nom = 'Feldschlosschen'));

insert into alcool (pourcentage, contenance, notemoyenne, provenance, style, boisson)
            values (3.4,0.5,0,
                    (select id from provenance where brasserie = 'Universal Brasserie'),
                    (select id from style where nom = 'Blonde'),
                    (select id from boissons where nom = 'Prix Garanti'));

insert into alcool (pourcentage, contenance, notemoyenne, provenance, style, boisson)
            values (3.4,0.3,0,
                    (select id from provenance where brasserie = 'Universal Brasserie'),
                    (select id from style where nom = 'Blonde'),
                    (select id from boissons where nom = 'Chimay'));

insert into alcool (pourcentage, contenance, notemoyenne, provenance, style, boisson)
            values (3.4,0.3,0,
                    (select id from provenance where brasserie = 'Universal Brasserie'),
                    (select id from style where nom = 'Blonde'),
                    (select id from boissons where nom = 'Delirium'));

-- Insertion des commentaires dans la table commentaire
insert into commentaire (note, comment, commentby, numeroboisson)
                 values (4,'Mon dieu cest trop cool',
                         'xxx_DarkEnzo_xxx',2);
insert into commentaire (note, comment, commentby, numeroboisson)
                 values (1,'cé pa bon',
                         'xxx_DarkYohann_xxx',3);

-- Insertion des transactions dans la table transaction
insert into transaction (addition, date, by)
            values (9,'20200305',
                    (select pseudo from utilisateur where pseudo = 'xxx_DarkMelvin_xxx'));

insert into transaction (addition, date, by)
            values (3,'20200605',
                    (select pseudo from utilisateur where pseudo = 'xxx_DarkEnzo_xxx'));

insert into transaction (addition, date, by)
            values (200,'20200306',
                    (select pseudo from utilisateur where pseudo = 'Paul'));

insert into transaction (addition, date, by)
            values (6,'20200406',
                    (select pseudo from utilisateur where pseudo = 'Marie'));

-- Insertion des boisson_transactions dans la table boisson_transaction
insert into boisson_transaction (numeroBoisson,numeroTransaction,quantite)
            values ((select id from boissons where nom = 'Coca'),(select id from transaction where by = 'Paul'),50);


insert into boisson_transaction (numeroBoisson,numeroTransaction,quantite)
            values ((select id from boissons where nom = 'Fanta'),(select id from transaction where by = 'Paul'),50);

insert into boisson_transaction (numeroBoisson,numeroTransaction,quantite)
            values ((select id from boissons where nom = 'BFM'),(select id from transaction where by = 'xxx_DarkEnzo_xxx'),1);

insert into boisson_transaction (numeroBoisson,numeroTransaction,quantite)
            values ((select id from boissons where nom = 'Cuvee'),(select id from transaction where by = 'xxx_DarkMelvin_xxx'),2);


insert into boisson_transaction (numeroBoisson,numeroTransaction,quantite)
            values ((select id from boissons where nom = 'Feldschlosschen'),(select id from transaction where by = 'Marie'),998);

-- vues --------------------------------------------------------
-- |
-- V

-- Toutes les boissons non alcoolisées
CREATE VIEW boisson_non_alcolise AS
    SELECT b.nom, quantitestock, prixvente, prixachat
FROM boissons b
    INNER JOIN type t on b.type = t.id
WHERE t.nom = 'Soft';

-- Toutes les boissons alcoolisées
CREATE VIEW boisson_alcolise AS
SELECT b.nom, b.quantitestock, b.prixvente,b.prixachat, pourcentage, contenance, notemoyenne, p.pays, p.region,p.brasserie, s.nom AS type
FROM alcool a
INNER JOIN provenance p on p.id = a.provenance
INNER JOIN boissons b on b.id = a.boisson
INNER JOIN style s on s.id = a.style
WHERE b.quantitestock > 0
ORDER BY notemoyenne DESC;

-- Le classement des bières
CREATE VIEW classement_biere AS
SELECT nom, notemoyenne
FROM boisson_alcolise
ORDER BY notemoyenne DESC;

-- Le classement des orientations
CREATE VIEW classement_orientation AS
SELECT o.nom, SUM(quantite) AS nb_biere
FROM orientation o
INNER JOIN utilisateur u on o.id = u.orientation
INNER JOIN transaction t on u.pseudo = t.by
INNER JOIN boisson_transaction bt on t.id = bt.numerotransaction
INNER JOIN boissons b on b.id = bt.numeroboisson
INNER JOIN type ty on b.type = ty.id
WHERE ty.nom = 'Alcool'
GROUP BY o.nom
ORDER BY nb_biere DESC;

--Retourne une liste de tous les users
CREATE VIEW list_usr AS
SELECT u.pseudo, email, password, o.nom orientaiton, admin
FROM utilisateur u
INNER JOIN orientation o on u.orientation = o.id
ORDER BY u.pseudo;

-- triggers --------------------------------------------------------
-- |
-- V

-- Fonction mise à jour de la moyenne d'une biere
CREATE FUNCTION maj_moyenne()

RETURNS trigger
LANGUAGE plpgsql
AS
$$
BEGIN
update alcool
set notemoyenne = (
            select
                avg(note)
            from commentaire c
            where c.numeroBoisson = new.numeroBoisson
            )
where alcool.id = new.numeroBoisson;
RETURN NULL;
END;
$$;

-- Trigger pour maj d'une bière après un commentaire
CREATE TRIGGER trigger_moyenne
AFTER INSERT
ON commentaire
FOR EACH ROW
EXECUTE FUNCTION maj_moyenne ();


-- Fonction mise à jour du stock après une transaction
CREATE FUNCTION soustraction_stock()
RETURNS trigger
LANGUAGE plpgsql
AS
$$
BEGIN
update boissons
set quantitestock = (
            select
                boissons.quantitestock-new.quantite
            from boissons b
            where b.id = new.numeroBoisson
            )
where boissons.id = new.numeroBoisson;
RETURN NULL;
END;
$$;

-- Trigger qui met à jour le stock après une transaction
CREATE TRIGGER soustraction_stock
AFTER INSERT
ON boisson_transaction
FOR EACH ROW
EXECUTE FUNCTION soustraction_stock ();

-- Fonction --------------------------------------------------------
-- |
-- V
-- Permet de lister les tables libres a une date donnée
CREATE FUNCTION table_libre (dateparam DATE)
RETURNS TABLE (numero int)
AS
$$
SELECT numero
FROM "table"
GROUP BY numero
HAVING numero NOT IN (
    SELECT numero
FROM "table" t
INNER JOIN table_event te on t.numero = te.numerotable
INNER JOIN evenement e on te.numeroevent= e.id
WHERE DATE = dateparam
    );
$$
language sql;

-- Permet de récupéré tous les commentaire d'une bière
CREATE FUNCTION get_all_comment (nombiere text)
RETURNS TABLE (commentby text, comment text, note int, nom text)
AS
$$
SELECT commentby, comment, note, b.nom
FROM commentaire c
INNER JOIN alcool a on a.id = c.numeroboisson
INNER JOIN boissons b on b.id = a.boisson
WHERE nom = nombiere;
$$
language sql;


-- Permet de vérifier si un user existe
CREATE FUNCTION login (usr text, pwd text)
RETURNS TABLE (pseudo text, email text, password text, orientation text, admin boolean)
AS
$$
SELECT u.pseudo, email, password, o.nom orientaiton, admin
FROM utilisateur u
INNER JOIN orientation o on u.orientation = o.id
WHERE u.pseudo = usr AND password = pwd;
$$
language sql;

-- Permet d'ajouter un commentaire en utilisant le nom d'un utilisateur
CREATE PROCEDURE add_comment (new_note int,new_comment text, pseudo text, nom_boisson text)
AS
$$
INSERT INTO commentaire (note, comment, commentby, numeroboisson)
                 values (new_note,new_comment,pseudo,(SELECT ID FROM boissons WHERE nom = nom_boisson));
$$
language sql;