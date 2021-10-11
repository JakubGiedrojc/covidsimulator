create table if not exists ENTRY_SIMULATION (
    ID NUMBER(19,0) PRIMARY KEY,
    NAME VARCHAR2(50),
    P NUMBER(19,0),
    I NUMBER(19,0),
    R NUMBER(19,2),
    M NUMBER(19,2),
    TI NUMBER(19,0),
    TM NUMBER(19,0),
    TS NUMBER (19,0)
    );

create table if not exists SIMULATION_OUTPUT (
    ID NUMBER (19,0) PRIMARY KEY,
    PI NUMBER (19,2),
    PV NUMBER (19,2),
    PM NUMBER (19,2),
    PR NUMBER (19,2),
    ENTRY_ID NUMBER (19,0),
    CONSTRAINT FK_ENTRY_ID FOREIGN KEY (ENTRY_ID) REFERENCES ENTRY_SIMULATION(ID)
    );




   /* OUTPUT_ID NUMBER(19,0),
    CONSTRAINT FK_OUTPUT_ID
    FOREIGN KEY (OUTPUT_ID) REFERENCES SIMULATION_OUTPUT(ID))*/;









