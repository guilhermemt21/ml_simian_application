--liquibase formatted sql

--changeset guilhermemt21:1
create table dna_analysis (
  id int(11) UNSIGNED not null primary key auto_increment,
  dna_sequence LONGTEXT NOT NULL,
  dna_sequence_sha1 VARCHAR(255),
  dna_sequence_length INTEGER,
  is_simian TINYINT(1),
  UNIQUE KEY dna_sequence_unique (dna_sequence_sha1, dna_sequence_length)
);