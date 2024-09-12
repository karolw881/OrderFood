PGDMP                      |           zamowienia_db    15.7    16.3     	           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            
           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    24590    zamowienia_db    DATABASE     �   CREATE DATABASE zamowienia_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Polish_Poland.1250';
    DROP DATABASE zamowienia_db;
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false                       0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    4            O           1247    24639    zamowienie_typ    TYPE     a   CREATE TYPE public.zamowienie_typ AS ENUM (
    'pyszne',
    'piechota',
    'telefonicznie'
);
 !   DROP TYPE public.zamowienie_typ;
       public          postgres    false    4            �            1259    24614    pozycja_zamowienie    TABLE       CREATE TABLE public.pozycja_zamowienie (
    id integer NOT NULL,
    cena double precision,
    opis character varying(255),
    sposob_platnosci character varying(255),
    status character varying(255),
    zamawiajacy character varying(255),
    id_zamowienia bigint
);
 &   DROP TABLE public.pozycja_zamowienie;
       public         heap    postgres    false    4            �            1259    24635    pozycja_zamowienie_seq    SEQUENCE     �   CREATE SEQUENCE public.pozycja_zamowienie_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.pozycja_zamowienie_seq;
       public          postgres    false    4            �            1259    24603 
   zamowienie    TABLE     �   CREATE TABLE public.zamowienie (
    id bigint NOT NULL,
    link character varying(255),
    do_kiedy character varying(255),
    typ character varying(255)
);
    DROP TABLE public.zamowienie;
       public         heap    postgres    false    4            �            1259    24602    zamowienie_id_seq    SEQUENCE     �   CREATE SEQUENCE public.zamowienie_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.zamowienie_id_seq;
       public          postgres    false    4    215                       0    0    zamowienie_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.zamowienie_id_seq OWNED BY public.zamowienie.id;
          public          postgres    false    214            �            1259    24636    zamowienie_seq    SEQUENCE     x   CREATE SEQUENCE public.zamowienie_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.zamowienie_seq;
       public          postgres    false    4            n           2604    24621    zamowienie id    DEFAULT     n   ALTER TABLE ONLY public.zamowienie ALTER COLUMN id SET DEFAULT nextval('public.zamowienie_id_seq'::regclass);
 <   ALTER TABLE public.zamowienie ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    215    215                      0    24614    pozycja_zamowienie 
   TABLE DATA           r   COPY public.pozycja_zamowienie (id, cena, opis, sposob_platnosci, status, zamawiajacy, id_zamowienia) FROM stdin;
    public          postgres    false    216   ^                 0    24603 
   zamowienie 
   TABLE DATA           =   COPY public.zamowienie (id, link, do_kiedy, typ) FROM stdin;
    public          postgres    false    215   �                  0    0    pozycja_zamowienie_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.pozycja_zamowienie_seq', 2201, true);
          public          postgres    false    217                       0    0    zamowienie_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.zamowienie_id_seq', 76, true);
          public          postgres    false    214                       0    0    zamowienie_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.zamowienie_seq', 551, true);
          public          postgres    false    218            r           2606    24694 *   pozycja_zamowienie pozycja_zamowienie_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.pozycja_zamowienie
    ADD CONSTRAINT pozycja_zamowienie_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.pozycja_zamowienie DROP CONSTRAINT pozycja_zamowienie_pkey;
       public            postgres    false    216            p           2606    24696    zamowienie zamowienie_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.zamowienie
    ADD CONSTRAINT zamowienie_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.zamowienie DROP CONSTRAINT zamowienie_pkey;
       public            postgres    false    215            s           2606    24712     pozycja_zamowienie fk_zamowienie    FK CONSTRAINT     �   ALTER TABLE ONLY public.pozycja_zamowienie
    ADD CONSTRAINT fk_zamowienie FOREIGN KEY (id_zamowienia) REFERENCES public.zamowienie(id);
 J   ALTER TABLE ONLY public.pozycja_zamowienie DROP CONSTRAINT fk_zamowienie;
       public          postgres    false    216    215    3184               =  x����n�0���Y�������V꩗J+�TK �ɢn(O�63PBf��R��{`p��U�)7js�7����ky���ޫ�w��++n�y�R�vצ����N��]��(\�"�RX�%A���FoO���e���Fu�Ѩp��'�a¸S�f�&?��.~5�_�HTuU�^|�s�n��
\8_C��j���R���ʨRm�V��90x�?4������.��]�����3�Ìڨ=k�~�nQ�k�]� K�AhD��te7�gV>�J�8L�W )CU}{'�72�\o�rEaR�\�a��Msm���p�����0/R��j��!���TF���IW���8�i1��a�#�(
I�a�$�$rIXA�t�G8I /<ݵ]ߑ�Q�ƊƉD�ECE9M�uXH?V�1k�-<��F��ijFc%��Yt�Ǫ\P�R���/�z|`y�dq>�4����b�x.p+p'p�x`y�|>$����3,K4q?+(�pA�Y�ϻ=`58���,���й�i4u��J�I&Q.̢*�)� �Bé|��3���}��-�B�Br���1�i������40�@�gC˟�h;�=���Y��F��|���|����{��C�Z;��V$�0LG�r���b�p0ps�h����b�����]7�|�(�J���7�a&�X^��[cZ�)o*�0~~8=��C2�ǛS�_T��,���<�K���d�I��z�9aH����yv@�Y�֠�-�	��㉄��ܬ�M���3�&9w�y���&ā�E��l����i�Y���=	�l�*@��ʲ�?��A         >  x�m�_o�0şo?E?�B���Fx_��hb|A����c��~u��,�s.������P<�0�8�|��(MF�m�E��1�vb���v�� <�6CC�f��йݵ�C�B �,�%����_�V��O/'�x_P9+gtܶ���\�P��5��1#]ͫ9ͫt!� %&-�Ӊ�@��H�=}�׏u�tsi.����M�S�uO����@uMƾ:�����۞�We��G�'�a��g6�/�e��^x^d�"�ͅ����D>'�9����8�"�(��g���Tz�=�d�ك� 3�,3�.���2�n 7��zQJ� /r�{     