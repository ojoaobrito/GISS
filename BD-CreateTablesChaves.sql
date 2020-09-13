
-------------------------------------------------------------------------------
-- USE DBProject: Changes the database context to the DBProject database.
--
USE Projeto
--
-------------------------------------------------------------------------------

-------------------------------------------------------------------------------
-- Criar as tabelas
-- (create the database tables)

-- ............................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[CentroHospitalar]') )
begin
  CREATE TABLE CentroHospitalar (
	  IdCentroHospitalar int NOT NULL
		  CHECK (IdCentroHospitalar >= 1),
	  Nome nvarchar (30) NOT NULL,
	  Regiao nvarchar (30) NOT NULL,

	  PRIMARY KEY (IdCentroHospitalar),
  );
end

-- ............................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Utente]') )
begin
  CREATE TABLE Utente (
	  IdUtente int NOT NULL 
	     CHECK (IdUtente >= 1),                    -- constraint type: check
	  IdCentroHospitalar int FOREIGN KEY REFERENCES CentroHospitalar(IdCentroHospitalar),
      Nome nvarchar (30)  NOT NULL,            -- NULL Column
	  DataRegisto nvarchar (30) NOT NULL,		-- com o formato "dd-mm-aaaa"

	  PRIMARY KEY (Idutente),
          
  ); 
    
end

-- ............................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Empregado]') )
begin
  CREATE TABLE Empregado (
	  IdEmpregado int NOT NULL 
	     CHECK (IdEmpregado >= 1),
	  IdCentroHospitalar int FOREIGN KEY REFERENCES CentroHospitalar(IdCentroHospitalar),
	  Nome nvarchar (30) NOT NULL,
	  Funcao nvarchar (30) NOT NULL,

	  PRIMARY KEY (IdEmpregado),
  );
end

-- ............................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Especializacao]') )
begin
  CREATE TABLE Especializacao (
	  IdEmpregado int FOREIGN KEY REFERENCES Empregado(IdEmpregado),
	  Especializacao nvarchar (30) NOT NULL,

	  PRIMARY KEY (IdEmpregado,Especializacao),
  );
end

-- ............................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Sala]') )
begin
  CREATE TABLE Sala (
	  IdSala int NOT NULL 
	     CHECK (IdSala >= 1),
	  IdHospital int NOT NULL,
	  IdArea int NOT NULL,
	  TipoSala nvarchar (30),

	  PRIMARY KEY (IdSala,IdHospital,IdArea),
  );
end

-- ............................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Ocorrencia]') )
begin
  CREATE TABLE Ocorrencia (
	  IdOcorrencia int NOT NULL 
	     CHECK (IdOcorrencia >= 1),
	  IdUtente int FOREIGN KEY REFERENCES Utente(IdUtente)
		 CHECK (IdUtente >= 1),
      IdCentroHospitalar int FOREIGN KEY REFERENCES CentroHospitalar(IdCentroHospitalar),
	  TipoOcorrencia nvarchar (30) NOT NULL,
	  Hora int NOT NULL
		 DEFAULT 7,
	  DataOcorrencia nvarchar (30)  NOT NULL,
	  Estado nvarchar (30) NOT NULL,
	  IdSala int,
	  Diagnostico nvarchar (50),

	  PRIMARY KEY (IdOcorrencia),
	   
  );
end

-- ............................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Liga]') )
begin
  CREATE TABLE Liga (
	  IdOcorrenciaInicial int FOREIGN KEY REFERENCES Ocorrencia(IdOcorrencia)			      --ID da ocorrência inicial
	     CHECK (IdOcorrenciaInicial >= 1),
	  IdOcorrenciaDerivada int FOREIGN KEY REFERENCES Ocorrencia(IdOcorrencia)                --ID da ocorrência derivada
	     CHECK (IdOcorrenciaDerivada >= 1),
	  Motivo nvarchar (20) NOT NULL,                    --motivo pelo qual se gerou uma nova ocorrência

	  PRIMARY KEY (IdOcorrenciaInicial,IdOcorrenciaDerivada),  
  );
end

-- ............................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Equipa]') )
begin
  CREATE TABLE Equipa (
	  IdOcorrencia int FOREIGN KEY REFERENCES Ocorrencia(IdOcorrencia),
	  IdEmpregado int FOREIGN KEY REFERENCES Empregado(IdEmpregado),
	  
	  PRIMARY KEY (IdOcorrencia,IdEmpregado)

  );
end

-- ............................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Cama]') )
begin
  CREATE TABLE Cama (
	  IdCama int NOT NULL 
	     CHECK (IdCama >= 1),
	  IdSala int NOT NULL,
	  Estado nvarchar (30) NOT NULL,

	  PRIMARY KEY (IdCama,IdSala),
  );
end

-- ............................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Hospital]') )
begin
  CREATE TABLE Hospital (
	  IdHospital int NOT NULL
		  CHECK (IdHospital >= 1),
	  IdCentroHospitalar int FOREIGN KEY REFERENCES CentroHospitalar(IdCentroHospitalar),
	  Nome nvarchar (30) NOT NULL,

	  PRIMARY KEY (IdHospital),
  );
end

-- ............................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[AreaClinica]') )
begin
  CREATE TABLE AreaClinica (
	  IdArea int NOT NULL
		  CHECK (IdArea >= 1),
	  Nome nvarchar (30) NOT NULL,

	  PRIMARY KEY (IdArea),
  );
end

-- ............................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[HospitalArea]') )
begin
  CREATE TABLE HospitalArea (
	  IdHospital int FOREIGN KEY REFERENCES Hospital(IdHospital),
	  IdArea int FOREIGN KEY REFERENCES AreaClinica(IdArea), 

	  PRIMARY KEY (IdHospital,IdArea),
  );
end

-- ............................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[Centro]') )
begin
  CREATE TABLE Centro (
     IdCentro int NOT NULL
		  CHECK (IdCentro >= 1),
	  Nome nvarchar (30) NOT NULL,

	  PRIMARY KEY (IdCentro),
  );
end

-- ............................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[CentroArea]') )
begin
  CREATE TABLE CentroArea (
     IdCentro int FOREIGN KEY REFERENCES Centro(IdCentro), 
	 IdArea int FOREIGN KEY REFERENCES AreaClinica(IdArea), 

	 PRIMARY KEY (IdCentro, IdArea),
  );
end


-- ............................................................................

if not exists (select * from dbo.sysobjects 
               where id = object_id(N'[dbo].[HospitalCentro]') )
begin
  CREATE TABLE HospitalCentro (
	 IdHospital int FOREIGN KEY REFERENCES Hospital(IdHospital),
     IdCentro int FOREIGN KEY REFERENCES Centro(IdCentro),

	 PRIMARY KEY (IdHospital,IdCentro),
  );
end
