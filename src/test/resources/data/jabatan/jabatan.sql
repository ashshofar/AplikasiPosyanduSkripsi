insert into jabatan(jabatan_id, deskripsi, nama_jabatan)
values('1ac99d1b-cee9-4c60-bb42-05e5399c883c','Kader Posyandu','Petugas');

insert into jabatan(jabatan_id, deskripsi, nama_jabatan)
values('6b651276-f046-4ce9-b6d8-14bfeb42591c','Ketua Rw','RW');

insert into jabatan(jabatan_id, deskripsi, nama_jabatan)
values('aa0efeb2-556b-48ca-9043-69c7229c7bcb','Administrator','Admin');

insert into jabatan(jabatan_id, deskripsi, nama_jabatan)
values('c33d618b-a759-4d2f-b5d0-bf5bc5c26d71','Orang Tua Balita','Orangtua');

insert into jabatan(jabatan_id, deskripsi, nama_jabatan)
values('9ce32cfb-67f7-4410-93a1-3f15282a9811','Bidan yang Bertugas di Posyandu','Bidan');

SELECT * FROM user JOIN assigment on user_id = id_user_userId WHERE id_jabatan_jabatanId = 'c33d618b-a759-4d2f-b5d0-bf5bc5c26d71';