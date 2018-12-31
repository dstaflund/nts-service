-- 1.  Run this first
CREATE TABLE nts_maps (
                        name		VARCHAR(6)    PRIMARY KEY,
                        snippet	VARCHAR(40)   NULL,
                        parent  VARCHAR(6)    NULL,
                        north		DECIMAL(5,2)  NOT NULL,
                        south		DECIMAL(5,2)  NOT NULL,
                        east		DECIMAL(5,2)  NOT NULL,
                        west		DECIMAL(5,2)  NOT NULL
);

-- 2.  Then do an import into the database (of nts-map-dml.csv)

-- 3.  Then run the following query
UPDATE nts_maps
   SET parent = CASE
                    WHEN LENGTH(name) < 4 THEN NULL
                    WHEN LENGTH(name) = 4 THEN SUBSTRING(name FROM 1 FOR 3)
                    ELSE SUBSTRING(name FROM 1 FOR 4)
                END;

-- 4.  Then create the following indexes and constraints
CREATE INDEX idx_nts_maps_parent ON nts_maps (parent);
CREATE INDEX idx_nts_maps_north ON nts_maps (north);
CREATE INDEX idx_nts_maps_south ON nts_maps (south);
CREATE INDEX idx_nts_maps_east ON nts_maps (east);
CREATE INDEX idx_nts_maps_west ON nts_maps (west);

ALTER TABLE nts_maps ADD CONSTRAINT fk_nts_maps_parent_name FOREIGN KEY (parent) REFERENCES nts_maps(name);

-- 5.  Add some search fields
ALTER TABLE nts_maps ADD COLUMN search_name VARCHAR(6) NULL;
ALTER TABLE nts_maps ADD COLUMN search_parent VARCHAR(4) NULL;
ALTER TABLE nts_maps ADD COLUMN search_north VARCHAR(7) NULL;
ALTER TABLE nts_maps ADD COLUMN search_south VARCHAR(7) NULL;
ALTER TABLE nts_maps ADD COLUMN search_east VARCHAR(7) NULL;
ALTER TABLE nts_maps ADD COLUMN search_west VARCHAR(7) NULL;

-- 6.  Then populate the search fields
UPDATE nts_maps
   SET search_name = CASE
                        WHEN LENGTH(name) = 3
                        THEN CAST(CAST(name AS SMALLINT) AS VARCHAR)    -- Casting should strip leading zeros
                        ELSE CAST(CAST(SUBSTRING(name FROM 1 FOR 3) AS SMALLINT) AS VARCHAR) || SUBSTRING(name FROM 4)
                     END
     , search_parent = TRIM(LEADING '0' FROM parent)
     , search_north = CAST(north AS VARCHAR)
     , search_south = CAST(south AS VARCHAR)
     , search_east = CAST(east AS VARCHAR)
     , search_west = CAST(west AS VARCHAR);

-- 7.  Then index them
CREATE INDEX idx_nts_maps_search_name ON nts_maps (search_name);
CREATE INDEX idx_nts_maps_search_parent ON nts_maps (search_parent);
CREATE INDEX idx_nts_maps_search_north ON nts_maps (search_north);
CREATE INDEX idx_nts_maps_search_south ON nts_maps (search_south);
CREATE INDEX idx_nts_maps_search_east ON nts_maps (search_east);
CREATE INDEX idx_nts_maps_search_west ON nts_maps (search_west);
