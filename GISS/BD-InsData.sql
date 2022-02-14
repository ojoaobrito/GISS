-------------------------------------------------------------------------------
-- USE DBProject: Changes the database context to the DBProject database.
--
USE Projeto
--
-------------------------------------------------------------------------------
--
-------------------------------------------------------------------------------
------------  INSERT SOME DATA INTO TABLES  -----------------------------------
-------------------------------------------------------------------------------

-------------------------------------------------------------------------------
------------- Transaction mode: autocommit
-------------------------------------------------------------------------------

INSERT INTO CentroHospitalar(IdCentroHospitalar, Nome, Regiao) 
Values (1, 'Centro Hospitalar de Lisboa', 'Centro');

INSERT INTO CentroHospitalar(IdCentroHospitalar, Nome, Regiao) 
Values (2, 'Centro Hospitalar do Porto', 'Norte');



INSERT INTO Utente(IdUtente, IdCentroHospitalar, Nome, DataRegisto) 
Values (1, 1, 'José Ribeiro', '01-06-2018');

INSERT INTO Utente(IdUtente, IdCentroHospitalar, Nome, DataRegisto) 
Values (2, 2, 'Maria das Dores', '01-06-2018');

INSERT INTO Utente(IdUtente, IdCentroHospitalar, Nome, DataRegisto) 
Values (3, 2, 'Aquilino Santos', '01-06-2018');



INSERT INTO Hospital(IdHospital, IdCentroHospitalar, Nome)
VALUES (2, 2, 'Hospital D.Carlos')

INSERT INTO Hospital(IdHospital, IdCentroHospitalar, Nome)
VALUES (3, 1, 'Hospital Senhora das Dores')

INSERT INTO Hospital(IdHospital, IdCentroHospitalar, Nome)
VALUES (4, 2, 'Hospital da Beira Baixa')



INSERT INTO Sala(IdSala, IdHospital, IdArea, TipoSala)
Values (20, 2, 3, 'Consultas');

INSERT INTO Sala(IdSala, IdHospital, IdArea, TipoSala)
Values (21, 3, 5, 'Consultas');

INSERT INTO Sala(IdSala, IdHospital, IdArea, TipoSala)
Values (19, 2, 5, 'Consultas');

INSERT INTO Sala(IdSala, IdHospital, IdArea, TipoSala)
Values (24, 2, 5, 'Consultas');

INSERT INTO Sala(IdSala, IdHospital, IdArea, TipoSala)
Values (17, 2, 5, 'Consultas');



INSERT INTO Ocorrencia(IdOcorrencia, IdUtente, IdCentroHospitalar, TipoOcorrencia, Hora, DataOcorrencia, Estado, IdSala, Diagnostico) 
Values (1, 2, 2, 'Consulta', 9, '01-06-2018', 'Agendada', 20, null);

INSERT INTO Ocorrencia(IdOcorrencia, IdUtente, IdCentroHospitalar, TipoOcorrencia, Hora, DataOcorrencia, Estado, IdSala, Diagnostico) 
Values (2, 1, 1, 'Consulta', 11, '01-06-2018', 'Agendada', 21, null);

INSERT INTO Ocorrencia(IdOcorrencia, IdUtente, IdCentroHospitalar, TipoOcorrencia, Hora, DataOcorrencia, Estado, IdSala, Diagnostico) 
Values (3, 2, 2, 'Consulta', 10, '01-06-2018', 'Agendada', 20, null);

INSERT INTO Ocorrencia(IdOcorrencia, IdUtente, IdCentroHospitalar, TipoOcorrencia, Hora, DataOcorrencia, Estado, IdSala, Diagnostico) 
Values (4, 2, 2, 'Análises', 9, '03-06-2018', 'Agendada', 19, null);

INSERT INTO Ocorrencia(IdOcorrencia, IdUtente, IdCentroHospitalar, TipoOcorrencia, Hora, DataOcorrencia, Estado, IdSala, Diagnostico) 
Values (5, 2, 2, 'Exame', 10, '07-06-2018', 'Agendada', 24, null);

INSERT INTO Ocorrencia(IdOcorrencia, IdUtente, IdCentroHospitalar, TipoOcorrencia, Hora, DataOcorrencia, Estado, IdSala, Diagnostico) 
Values (6, 3, 2, 'Exame', 11, '07-06-2018', 'Agendada', 17, null);

INSERT INTO Ocorrencia(IdOcorrencia, IdUtente, IdCentroHospitalar, TipoOcorrencia, Hora, DataOcorrencia, Estado, IdSala, Diagnostico) 
Values (7, 2, 2, 'Consulta', 14, '12-06-2018', 'Agendada', 20, null);



INSERT INTO Empregado(IdEmpregado, IdCentroHospitalar, Nome, Funcao) 
Values (1, 2, 'Mário Rui', 'Médico(a)');

INSERT INTO Empregado(IdEmpregado, IdCentroHospitalar, Nome, Funcao) 
Values (2, 2, 'Paulo Azevedo', 'Médico(a)');

INSERT INTO Empregado(IdEmpregado, IdCentroHospitalar, Nome, Funcao) 
Values (3, 2, 'Jacinto Flores', 'Enfermeiro(a)');

INSERT INTO Empregado(IdEmpregado, IdCentroHospitalar, Nome, Funcao) 
Values (4, 2, 'João André', 'Técnico(a)');

INSERT INTO Empregado(IdEmpregado, IdCentroHospitalar, Nome, Funcao) 
Values (5, 1, 'Odete Silva', 'Enfermeiro(a)');

INSERT INTO Empregado(IdEmpregado, IdCentroHospitalar, Nome, Funcao) 
Values (6, 2, 'Cristiano Rosas', 'Técnico(a)');



INSERT INTO Especializacao(IdEmpregado, Especializacao) 
Values (1, 'Cardiologia');

INSERT INTO Especializacao(IdEmpregado, Especializacao) 
Values (2, 'Urologia');

INSERT INTO Especializacao(IdEmpregado, Especializacao) 
Values (4, 'Oftalmologia');



INSERT INTO Liga(IdOcorrenciaInicial, IdOcorrenciaDerivada, Motivo)
Values (3, 4, 'Risco de derrame');



INSERT INTO AreaClinica(IdArea, Nome)
Values (1, 'Fisiatria')

INSERT INTO AreaClinica(IdArea, Nome)
Values (2, 'Psicologia')

INSERT INTO AreaClinica(IdArea, Nome)
Values (3, 'Cardiologia')

INSERT INTO AreaClinica(IdArea, Nome)
Values (4, 'Cirurgia')

INSERT INTO AreaClinica(IdArea, Nome)
Values (5, 'Neurologia')

INSERT INTO AreaClinica(IdArea, Nome)
Values (6, 'Pediatria')

INSERT INTO AreaClinica(IdArea, Nome)
Values (7, 'Reumatologia')

INSERT INTO AreaClinica(IdArea, Nome)
Values (8, 'Raio-X')

INSERT INTO AreaClinica(IdArea, Nome)
Values (9, 'Análises')

INSERT INTO AreaClinica(IdArea, Nome)
Values (10, 'Fisioterapia')

INSERT INTO AreaClinica(IdArea, Nome)
Values (11, 'Geral')


INSERT INTO Equipa(IdOcorrencia, IdEmpregado)
Values (1, 1);

INSERT INTO Equipa(IdOcorrencia, IdEmpregado)
Values (1, 3);

INSERT INTO Equipa(IdOcorrencia, IdEmpregado)
Values (4, 4);

INSERT INTO Equipa(IdOcorrencia, IdEmpregado)
Values (3, 1);

INSERT INTO Equipa(IdOcorrencia, IdEmpregado)
Values (3, 3);

INSERT INTO Equipa(IdOcorrencia, IdEmpregado)
Values (5, 2);

INSERT INTO Equipa(IdOcorrencia, IdEmpregado)
Values (6, 2);

INSERT INTO Equipa(IdOcorrencia, IdEmpregado)
Values (7, 1);