PGDMP                  
    |         
   SiapSidang    16.4    16.4 )    ;           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            <           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            =           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            >           1262    16418 
   SiapSidang    DATABASE     n   CREATE DATABASE "SiapSidang" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
    DROP DATABASE "SiapSidang";
                postgres    false            �            1259    16428    dosen    TABLE     �   CREATE TABLE public.dosen (
    nik character varying(20) NOT NULL,
    nama character varying(50) NOT NULL,
    kode_nama character varying(5),
    email character varying(60),
    password character varying(50) NOT NULL
);
    DROP TABLE public.dosen;
       public         heap    postgres    false            �            1259    16494    komponen_nilai    TABLE     �   CREATE TABLE public.komponen_nilai (
    id_komp integer NOT NULL,
    deskripsi character varying(100) NOT NULL,
    peran character varying(15),
    bobot numeric(4,2) NOT NULL
);
 "   DROP TABLE public.komponen_nilai;
       public         heap    postgres    false            �            1259    16493    komponen_nilai_id_komp_seq    SEQUENCE     �   CREATE SEQUENCE public.komponen_nilai_id_komp_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.komponen_nilai_id_komp_seq;
       public          postgres    false    218            ?           0    0    komponen_nilai_id_komp_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.komponen_nilai_id_komp_seq OWNED BY public.komponen_nilai.id_komp;
          public          postgres    false    217            �            1259    16435 	   mahasiswa    TABLE     �   CREATE TABLE public.mahasiswa (
    npm character varying(10) NOT NULL,
    nama character varying(50) NOT NULL,
    email character varying(60),
    password character varying(50) NOT NULL
);
    DROP TABLE public.mahasiswa;
       public         heap    postgres    false            �            1259    16527    nilai_ta    TABLE     |   CREATE TABLE public.nilai_ta (
    id_ta integer NOT NULL,
    id_komp integer NOT NULL,
    nilai numeric(5,2) NOT NULL
);
    DROP TABLE public.nilai_ta;
       public         heap    postgres    false            �            1259    16543 	   sidang_ta    TABLE       CREATE TABLE public.sidang_ta (
    id_sidang integer NOT NULL,
    nik character varying(20),
    id_ta integer,
    peran character varying(15),
    hari character varying(10),
    tanggal date NOT NULL,
    waktu time without time zone NOT NULL,
    tempat character varying(15)
);
    DROP TABLE public.sidang_ta;
       public         heap    postgres    false            �            1259    16542    sidang_ta_id_sidang_seq    SEQUENCE     �   CREATE SEQUENCE public.sidang_ta_id_sidang_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.sidang_ta_id_sidang_seq;
       public          postgres    false    223            @           0    0    sidang_ta_id_sidang_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.sidang_ta_id_sidang_seq OWNED BY public.sidang_ta.id_sidang;
          public          postgres    false    222            �            1259    16516    tugas_akhir    TABLE     �  CREATE TABLE public.tugas_akhir (
    id_ta integer NOT NULL,
    jenis character varying(3) NOT NULL,
    judul character varying(255) NOT NULL,
    semester_akd character varying(6) NOT NULL,
    tahun_akd integer NOT NULL,
    nilai_pb1 numeric(5,2),
    nilai_pb2 numeric(5,2),
    nilai_pu1 numeric(5,2),
    nilai_pu2 numeric(5,2),
    nilai_koord numeric(5,2),
    nilai_akhir numeric(5,2),
    angka_akhir character(2),
    id_mahasiswa character varying(10)
);
    DROP TABLE public.tugas_akhir;
       public         heap    postgres    false            �            1259    16515    tugas_akhir_id_ta_seq    SEQUENCE     �   CREATE SEQUENCE public.tugas_akhir_id_ta_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.tugas_akhir_id_ta_seq;
       public          postgres    false    220            A           0    0    tugas_akhir_id_ta_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.tugas_akhir_id_ta_seq OWNED BY public.tugas_akhir.id_ta;
          public          postgres    false    219            �           2604    16497    komponen_nilai id_komp    DEFAULT     �   ALTER TABLE ONLY public.komponen_nilai ALTER COLUMN id_komp SET DEFAULT nextval('public.komponen_nilai_id_komp_seq'::regclass);
 E   ALTER TABLE public.komponen_nilai ALTER COLUMN id_komp DROP DEFAULT;
       public          postgres    false    217    218    218            �           2604    16546    sidang_ta id_sidang    DEFAULT     z   ALTER TABLE ONLY public.sidang_ta ALTER COLUMN id_sidang SET DEFAULT nextval('public.sidang_ta_id_sidang_seq'::regclass);
 B   ALTER TABLE public.sidang_ta ALTER COLUMN id_sidang DROP DEFAULT;
       public          postgres    false    222    223    223            �           2604    16519    tugas_akhir id_ta    DEFAULT     v   ALTER TABLE ONLY public.tugas_akhir ALTER COLUMN id_ta SET DEFAULT nextval('public.tugas_akhir_id_ta_seq'::regclass);
 @   ALTER TABLE public.tugas_akhir ALTER COLUMN id_ta DROP DEFAULT;
       public          postgres    false    219    220    220            0          0    16428    dosen 
   TABLE DATA           F   COPY public.dosen (nik, nama, kode_nama, email, password) FROM stdin;
    public          postgres    false    215   �1       3          0    16494    komponen_nilai 
   TABLE DATA           J   COPY public.komponen_nilai (id_komp, deskripsi, peran, bobot) FROM stdin;
    public          postgres    false    218   3       1          0    16435 	   mahasiswa 
   TABLE DATA           ?   COPY public.mahasiswa (npm, nama, email, password) FROM stdin;
    public          postgres    false    216   )4       6          0    16527    nilai_ta 
   TABLE DATA           9   COPY public.nilai_ta (id_ta, id_komp, nilai) FROM stdin;
    public          postgres    false    221   9       8          0    16543 	   sidang_ta 
   TABLE DATA           _   COPY public.sidang_ta (id_sidang, nik, id_ta, peran, hari, tanggal, waktu, tempat) FROM stdin;
    public          postgres    false    223   ;<       5          0    16516    tugas_akhir 
   TABLE DATA           �   COPY public.tugas_akhir (id_ta, jenis, judul, semester_akd, tahun_akd, nilai_pb1, nilai_pb2, nilai_pu1, nilai_pu2, nilai_koord, nilai_akhir, angka_akhir, id_mahasiswa) FROM stdin;
    public          postgres    false    220   ]>       B           0    0    komponen_nilai_id_komp_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.komponen_nilai_id_komp_seq', 25, true);
          public          postgres    false    217            C           0    0    sidang_ta_id_sidang_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.sidang_ta_id_sidang_seq', 50, true);
          public          postgres    false    222            D           0    0    tugas_akhir_id_ta_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.tugas_akhir_id_ta_seq', 50, true);
          public          postgres    false    219            �           2606    16434    dosen dosen_email_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.dosen
    ADD CONSTRAINT dosen_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public.dosen DROP CONSTRAINT dosen_email_key;
       public            postgres    false    215            �           2606    16432    dosen dosen_pkey 
   CONSTRAINT     O   ALTER TABLE ONLY public.dosen
    ADD CONSTRAINT dosen_pkey PRIMARY KEY (nik);
 :   ALTER TABLE ONLY public.dosen DROP CONSTRAINT dosen_pkey;
       public            postgres    false    215            �           2606    16499 "   komponen_nilai komponen_nilai_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public.komponen_nilai
    ADD CONSTRAINT komponen_nilai_pkey PRIMARY KEY (id_komp);
 L   ALTER TABLE ONLY public.komponen_nilai DROP CONSTRAINT komponen_nilai_pkey;
       public            postgres    false    218            �           2606    16441    mahasiswa mahasiswa_email_key 
   CONSTRAINT     Y   ALTER TABLE ONLY public.mahasiswa
    ADD CONSTRAINT mahasiswa_email_key UNIQUE (email);
 G   ALTER TABLE ONLY public.mahasiswa DROP CONSTRAINT mahasiswa_email_key;
       public            postgres    false    216            �           2606    16439    mahasiswa mahasiswa_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.mahasiswa
    ADD CONSTRAINT mahasiswa_pkey PRIMARY KEY (npm);
 B   ALTER TABLE ONLY public.mahasiswa DROP CONSTRAINT mahasiswa_pkey;
       public            postgres    false    216            �           2606    16531    nilai_ta nilai_ta_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.nilai_ta
    ADD CONSTRAINT nilai_ta_pkey PRIMARY KEY (id_ta, id_komp);
 @   ALTER TABLE ONLY public.nilai_ta DROP CONSTRAINT nilai_ta_pkey;
       public            postgres    false    221    221            �           2606    16548    sidang_ta sidang_ta_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.sidang_ta
    ADD CONSTRAINT sidang_ta_pkey PRIMARY KEY (id_sidang);
 B   ALTER TABLE ONLY public.sidang_ta DROP CONSTRAINT sidang_ta_pkey;
       public            postgres    false    223            �           2606    16521    tugas_akhir tugas_akhir_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.tugas_akhir
    ADD CONSTRAINT tugas_akhir_pkey PRIMARY KEY (id_ta);
 F   ALTER TABLE ONLY public.tugas_akhir DROP CONSTRAINT tugas_akhir_pkey;
       public            postgres    false    220            �           2606    16537    nilai_ta nilai_ta_id_komp_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.nilai_ta
    ADD CONSTRAINT nilai_ta_id_komp_fkey FOREIGN KEY (id_komp) REFERENCES public.komponen_nilai(id_komp) ON DELETE CASCADE;
 H   ALTER TABLE ONLY public.nilai_ta DROP CONSTRAINT nilai_ta_id_komp_fkey;
       public          postgres    false    218    221    3477            �           2606    16532    nilai_ta nilai_ta_id_ta_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.nilai_ta
    ADD CONSTRAINT nilai_ta_id_ta_fkey FOREIGN KEY (id_ta) REFERENCES public.tugas_akhir(id_ta) ON DELETE CASCADE;
 F   ALTER TABLE ONLY public.nilai_ta DROP CONSTRAINT nilai_ta_id_ta_fkey;
       public          postgres    false    3479    221    220            �           2606    16554    sidang_ta sidang_ta_id_ta_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.sidang_ta
    ADD CONSTRAINT sidang_ta_id_ta_fkey FOREIGN KEY (id_ta) REFERENCES public.tugas_akhir(id_ta) ON DELETE CASCADE;
 H   ALTER TABLE ONLY public.sidang_ta DROP CONSTRAINT sidang_ta_id_ta_fkey;
       public          postgres    false    3479    220    223            �           2606    16549    sidang_ta sidang_ta_nik_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.sidang_ta
    ADD CONSTRAINT sidang_ta_nik_fkey FOREIGN KEY (nik) REFERENCES public.dosen(nik) ON DELETE CASCADE;
 F   ALTER TABLE ONLY public.sidang_ta DROP CONSTRAINT sidang_ta_nik_fkey;
       public          postgres    false    215    3471    223            �           2606    16522 )   tugas_akhir tugas_akhir_id_mahasiswa_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.tugas_akhir
    ADD CONSTRAINT tugas_akhir_id_mahasiswa_fkey FOREIGN KEY (id_mahasiswa) REFERENCES public.mahasiswa(npm) ON DELETE CASCADE;
 S   ALTER TABLE ONLY public.tugas_akhir DROP CONSTRAINT tugas_akhir_id_mahasiswa_fkey;
       public          postgres    false    216    3475    220            0     x�u��j�0E��_�/2y��qB0�W�<lkV��u�,J�6�}��Ay��I�E��DCZP|�+I��l��?7L����/42j��p!kf��,����{�5i���t 1�9Nd[�8M�_��@��CO8|n��C ��s줰t�U%�+�����/� �����l���{��|��0��QH��/�Pӏ2�C�=a|e����1��'>�@m,��+��G5��yL��>+��F�a�#MPެ�I�_%z{�
Xd�7����r�      3   
  x�u��n� ����	"/��s�!��U��^V*j�� G����LJ����?gr���(-{��6�`8"4���+^�x7㦈�x�na0�
��ʕi���l��w�]�8�7Y
.Q�?�0�2���|JEN���5B}M��b��Y�C,�Ty	��b���Ǌ��_�\I.W���OG��{2R8�z#�ɛ��c����ρﾤ2�?�׾�v����6r!1�s,b��y	��7��3Zx������Z9��֮D��}l���L(�L      1   �  x�]��Z�8ǯ�S�	����0��p�ef��4%&i�M:��WrZb禟�R+�ϒ'j��TR*X�k\&��}8@�Ŋm��x]�+�K��n�J��L��N����)%���3kѐ�z�;���r�5p�cS�,�k��x���Z����k�;z�4�3,|q&O��K�������=��?�rS�Z��n��AȊZl{[�A.��/2x�	�����AG���e�(�$��mِ<�?]I�M�f�«�b�K9�)��&�C�.�_Tt��g��s��R΂t����s�lVX%7ؕ��c%ֽ�� �G��LW��~Ϫ����V�M�B� �����+W�p�b��C(��mR�~.[�!S�u׮\r�>
%ۢa[��y��q�n�WL���:X�)�;��n��𡔅[���hU,l��-P��sHO�{���mZ��@���E������/6Y�;�
�w*���ɐ2���f�\R)9�G�pl��&v�i�93�wD����4$��(U���l��sx@���Ϋ�-��cS�(�[�?s����%�t�d��M�n-�j��GJ�s*����t�y���5����:۶T��i-{�C|8�cz��'��]r���[رy�tȐ�h�[��q�(݊>�����u���� �¯������+ؓ�Q�"�4^{~��Eǧ��@�u�R���8u
Tq�(���W [:���:fI��[�����jx&!^z�C�8���9�9".�r����B�,���ے��%�Qw��N
,ɦĶ	�b���2
�6����A�.�&��2c�s��[:�+�EŶ	��~f̓�7������7
�D��E�e,\dղ���{yc&d�?!�Kjq紧�g7a-VaB�(`��[O���2Y</;��9��x�&�̇L����6�+��`��.L�!`��
fp�'��o��,����x�9�8e��k�����m��~3�k%����q|�*?�{aC�8`c⬂�T"ݴMm�ɦ��6d��Vř�f��xd��6�7�6�pq��q�:>m�m��t�?��ӖWGiM��r�EX���mW&��8���,��z>l̛M��t��F�	Z<�Hؐ7�c���tK��Q8j�l�� ۆ�y��Y�S5}�~P���O*;�F ��3;�'� ������ڐ1�1dv?��e�&*
~{�͉KA�#6nl��_]�>Gm�;��4��<���899��̯      6      x�M�[�� D�=��K�|ͥ�?�� �{��!����_��VF��o�r��5){�b[�4[����0���[;eO_k�}�7{/��zY7{/��-�E:FR�[��}�����Q����>�w�a⎯���*�?c��/�Tq�g�8O4Sܼ�W�ݗ���(n]q�㾳U\мo�W�N�v:w
n����;&.�S�>�Gq͓���u�i�*h����-�6��5�I��=�E��_�5C��8#]�3�U��ˍ_���(�h��5R�:Ҝ�U?�G�IyjosfJ�&�s�<vcڰ\�#�-�D�G��m�΋q��W4�E��w
s^���q���R�"r5�Z@�q�*�ħK��fr[DK��qbb���qW��*�V��U�U�, ����UyZ��ħx;$[�"�8\��:��2� |U��$�j!3]�l�2�Z݀I����f�E��X �)u嵒�R7kU����w�1���|']=��y\=�Uk�İ蕵ڳ�(R�1gCc�*F�WW{KW��ĺ�UE�jH��z1�1n:]Ud�n�F k՘�))�V�j�#u�("W�ZY@�jLW'�∈�R���f��f��J�d@��h̽K�ܻD�+�'�F��I��;�bԪb�U�7�8��r(�\WQ�U�� h����
�G���7��U ��9� Y �, t^+�B�@�&<�
`�R*8X��@��AW1X �W=���V�5����Z�LW�i�gZa��H���
둺Ww��y��Y �Y x�q��
9���
�zR���RGN�Q�V�3����~������      8     x�u�KKQ��u�QN]�r\f7q�M�d!x��?g�Ő��`P����Rqk�T{o��m���o*�ޞ�Ěō��x�����k�q��x�6Y�7�1oc�e�ܯL������-yxR���������ƭM��Cb����ϟ���<?��Yf���-߿^����h���a��� �uZ��*+&�s����`�-'��y�K�Us�������'5�P2\����C�s��)!*J�sT��`Qל�%	Ĩ�Q7�r��=j�Y^A*�4�,���Is�V�(��J�r$�,�\ڌ�lC��.m�,]���<ʱ�G.ȳ�c7��K��局;�Х�\�%]Zry���4tie�Mi���.�ܕ�.m�Ymi��:dY���KO.�ܗ�.�\}����eї����eї�.�\}����eї�.�\}����eї�.\}����eї�.�]}�2���t಼(]FrYߔ|Tf��U�2�eyW��첼,]�,o�@��\��e���.��rF�3�,�˟��4��ԈZ      5   �  x��X�r�8<_���]ă���8Yo��c{��^ 	�a�T$�-���Eb@J�ͅf�*�z���A�zY�պ��e=�~ut��h���2Z�|/jY�>��MV+�ʓ�8^幹��J�ue���u�JqNH��#b�<�� �-o����J��Ħ��JD��=ox��$~���D?+352S#352�$^݀JQU���iuh;9�\=���N�����*,��`��2s_8���s�P�����8�s��5��/UK��Q�JL��.�t�\o��uJL���X��4�F�"ʦ�ڃ�n�vW�^�l���8R�&7�l>���g��i/�ϼ?���T�V|j׈��i�9$`&v�d4
Эe���F?r�w��у�]���y���{Y�2�c�`A�n �����d(_��M���[;�z�"mb��a�=�9���´k����G4j4��]{�4��m���w��df0Y
�hF�TꞆ�.�l����TF�5��V�Ya�V`�\K��O�霯�c���亥:(+��Ը±XM��汎�h�@3����%:�h7�FB�0M*O0k����˃na�0-mR�Cu[��}t��Ǔ�uN�^L���5Lfɦu�( �n�>������Y�������u(	3������n8�*z
��n�yw5j+t:@�wE����,�D�pz��n��`t��R=�E����%`I�N�4�h�p����+��m���z܇����Hz�w�8C8�Bq%\
����*�N�ڳ�+ܵ��#\X=YgU����)������Bu�{h�E�@d�
U��^j�Sc����h#ˮ�U�3πtH&� ^BbD�EL+=z��cھl�s�yp�b[�%GT� F�,���ie�=ֽ�C3	L����!�Х�>q��˒�\����|]�L�" +%�m�иR7��z�~K@�ē�-����u��$�$�c��&�M!P3\/�u�~36���<;��p�����Kj��� at�L��<�
��yJ����P W�H�J�xk;�Q���م��EJBL9"�U�T�ۊ������[�AЙ�Kb� �X �F�����G�ճh�"Mꂍ���˛ƈZ3��5y��飽0uZ��nTdlvf�-�y^m#J�w��D�8���Gn���q�V0�㸌B���B/-���7Qr��'�U���f��u��+E������4f����X	���`~���@���GM���O�N���,̀¸@DSh���yW�.�2�5d�8x�AT�n��y��.N0��3��Kgj��'y�1����/����RO�Au�����@��y;�3�е�*�}�> �XVX�����-�3���́&�%Ix�Ld1b��a��2��5��̑��/��=�[&�i��U�"���ϛ3�;`�q�a����>���jx��+R�-ϥ�%L'��E��ǿ6�O5��y)� q�H�,XIᩛ1�p|J\����!�Bf$��oi���ȓ v��i9���)�(�؆[�/껇�I�N�>���	��Y�X���x
2�}��ȏj��r3E.�<-�!���!�A�����	�3�_=A/B����U	F���>�F�������)��k���"�=o����/��;�yr�e6{o���%1��!��s4     