-- default
create table if not exists DbDefault
(
    defaultId      text,
    defaultEngine  text,
    defaultCharset text,
    defaultCollate text,
    primary key (defaultId)
);

-- dict
create table if not exists DbDictColumnType
(
    dictColumnTypeId text,
    seq              integer,
    columnType       text,
    remarks          text,
    primary key (dictColumnTypeId),
    unique (columnType)
);
create index if not exists DbDictColumnTypeIdx01 on DbDictColumnType (seq, columnType);

create table if not exists DbDictColumn
(
    dictColumnId            text,
    checksum                text,
    columnName              text,
    columnComment           text,
    dictColumnTypeId        text,
    notNullValue            text,
    charsetValue            text,
    collateValue            text,
    defaultValue            text,
    onUpdate                text,
    autoIncrementDefinition text,
    option                  text,
    primary key (dictColumnId),
    unique (checksum),
    foreign key (dictColumnTypeId) references DbDictColumnType (dictColumnTypeId)
);
create index if not exists DbDictColumnIdx01 on DbDictColumn (dictColumnTypeId);
create index if not exists DbDictColumnIdx02 on DbDictColumn (columnName, columnComment);

create table if not exists DbDictGroup
(
    dictGroupId text,
    groupName   text,
    primary key (dictGroupId),
    unique (groupName)
);

create table if not exists DbDictGroupColumn
(
    dictGroupId  text,
    seq          integer,
    dictColumnId text,
    primary key (dictGroupId, seq),
    unique (dictGroupId, dictColumnId),
    foreign key (dictGroupId) references DbDictGroup (dictGroupId) on delete cascade,
    foreign key (dictColumnId) references DbDictColumn (dictColumnId)
);
create index if not exists DbDictGroupColumnIdx01 on DbDictGroupColumn (dictColumnId);

create table if not exists DbDictPartition
(
    dictPartitionId text,
    partitionName   text,
    expression      text,
    primary key (dictPartitionId),
    unique (partitionName)
);

-- table
create table if not exists DbTable
(
    tableId            text,
    tableName          text,
    tableComment       text,
    engine             text,
    charsetValue       text,
    collateValue       text,
    autoIncrementValue text,
    option             text,
    primary key (tableId),
    unique (tableName)
);

create table if not exists DbTableOption
(
    tableId text,
    posX    integer,
    posY    integer,
    color   integer,
    primary key (tableId),
    foreign key (tableId) references DbTable (tableId) on delete cascade
);

create table if not exists DbTableColumn
(
    tableId         text,
    ordinalPosition integer,
    dictColumnId    text,
    primary key (tableId, ordinalPosition),
    unique (tableId, dictColumnId),
    foreign key (tableId) references DbTable (tableId) on delete cascade,
    foreign key (dictColumnId) references DbDictColumn (dictColumnId)
);
create index if not exists DbTableColumnIdx01 on DbTableColumn (dictColumnId);

create table if not exists DbTableGroup
(
    tableId     text,
    dictGroupId text,
    primary key (tableId),
    foreign key (tableId) references DbTable (tableId) on delete cascade,
    foreign key (dictGroupId) references DbDictGroup (dictGroupId)
);
create index if not exists DbTableGroupIdx01 on DbTableGroup (dictGroupId);

create table if not exists DbTablePartition
(
    tableId         text,
    dictPartitionId text,
    primary key (tableId),
    foreign key (tableId) references DbTable (tableId) on delete cascade,
    foreign key (dictPartitionId) references DbDictPartition (dictPartitionId)
);
create index if not exists DbTablePartitionIdx01 on DbTablePartition (dictPartitionId);

-- primary key
create table if not exists DbTablePrimaryKey
(
    tableId        text,
    seq            integer,
    constraintName text,
    keyName        text,
    indexComment   text,
    indexType      text,
    primary key (tableId, seq),
    unique (tableId, keyName),
    foreign key (tableId) references DbTable (tableId) on delete cascade
);

create table if not exists DbTablePrimaryKeyColumn
(
    tableId      text,
    seq          integer,
    dictColumnId text,
    length       text,
    seqInIndex   text,
    collation    text,
    primary key (tableId, seq, dictColumnId),
    foreign key (tableId) references DbTable (tableId) on delete cascade,
    foreign key (dictColumnId) references DbDictColumn (dictColumnId)
);
create index if not exists DbTablePrimaryKeyColumnIdx01 on DbTablePrimaryKeyColumn (dictColumnId);

-- unique key
create table if not exists DbTableUniqueKey
(
    tableId        text,
    seq            integer,
    constraintName text,
    keyName        text,
    indexComment   text,
    indexType      text,
    primary key (tableId, seq),
    unique (tableId, keyName),
    foreign key (tableId) references DbTable (tableId) on delete cascade
);

create table if not exists DbTableUniqueKeyColumn
(
    tableId      text,
    seq          integer,
    dictColumnId text,
    length       text,
    seqInIndex   text,
    collation    text,
    primary key (tableId, seq, dictColumnId),
    foreign key (tableId) references DbTable (tableId) on delete cascade,
    foreign key (dictColumnId) references DbDictColumn (dictColumnId)
);
create index if not exists DbTableUniqueKeyColumnIdx01 on DbTableUniqueKeyColumn (dictColumnId);

-- key
create table if not exists DbTableKey
(
    tableId      text,
    seq          integer,
    keyName      text,
    indexComment text,
    indexType    text,
    primary key (tableId, seq),
    unique (tableId, keyName),
    foreign key (tableId) references DbTable (tableId) on delete cascade
);

create table if not exists DbTableKeyColumn
(
    tableId      text,
    seq          integer,
    dictColumnId text,
    length       text,
    seqInIndex   text,
    collation    text,
    primary key (tableId, seq, dictColumnId),
    foreign key (tableId) references DbTable (tableId) on delete cascade,
    foreign key (dictColumnId) references DbDictColumn (dictColumnId)
);
create index if not exists DbTableKeyColumnIdx01 on DbTableKeyColumn (dictColumnId);

-- foreign key
create table if not exists DbTableForeignKey
(
    tableId          text,
    seq              integer,
    constraintName   text,
    keyName          text,
    referenceTableId text,
    onUpdate         text,
    onDelete         text,
    -- 0..1 -> 1
    -- 1 -> 1
    -- 0..N -> 1
    -- N -> 1
    relationship     text,
    primary key (tableId, seq),
    unique (tableId, keyName),
    unique (tableId, referenceTableId),
    unique (constraintName),
    foreign key (tableId) references DbTable (tableId) on delete cascade,
    foreign key (referenceTableId) references DbTable (tableId)
);
create index if not exists DbTableForeignKeyIdx01 on DbTableForeignKey (referenceTableId);

create table if not exists DbTableForeignKeyColumn
(
    tableId               text,
    seq                   integer,
    dictColumnId          text,
    referenceDictColumnId text,
    seqInIndex            text,
    primary key (tableId, seq, dictColumnId),
    foreign key (tableId) references DbTable (tableId) on delete cascade,
    foreign key (dictColumnId) references DbDictColumn (dictColumnId),
    foreign key (referenceDictColumnId) references DbDictColumn (dictColumnId)
);
create index if not exists DbTableForeignKeyColumnIdx01 on DbTableForeignKeyColumn (dictColumnId);
create index if not exists DbTableForeignKeyColumnIdx02 on DbTableForeignKeyColumn (referenceDictColumnId);

-- check
create table if not exists DbTableCheck
(
    tableId        text,
    seq            integer,
    constraintName text,
    expression     text,
    primary key (tableId, seq),
    unique (constraintName),
    foreign key (tableId) references DbTable (tableId) on delete cascade
);

-- sequence
create table if not exists DbSequence
(
    sequenceId     text,
    sequenceName   text,
    startValue     text,
    minimumValue   text,
    maximumValue   text,
    incrementValue text,
    cacheSize      text,
    cycle          text,
    primary key (sequenceId),
    unique (sequenceName)
);

create table if not exists DbSequenceOption
(
    sequenceId text,
    posX       integer,
    posY       integer,
    color      integer,
    primary key (sequenceId),
    foreign key (sequenceId) references DbSequence (sequenceId) on delete cascade
);

-- note
create table if not exists DbNote
(
    noteId  text,
    subject text,
    body    text,
    primary key (noteId),
    unique (subject)
);

create table if not exists DbNoteOption
(
    noteId text,
    posX   integer,
    posY   integer,
    color  integer,
    width  integer,
    height integer,
    primary key (noteId),
    foreign key (noteId) references DbNote (noteId) on delete cascade
);

-- connector
create table if not exists DbNoteConnectorTable
(
    noteId  text,
    tableId text,
    primary key (noteId, tableId),
    foreign key (noteId) references DbNote (noteId) on delete cascade,
    foreign key (tableId) references DbTable (tableId) on delete cascade
);
create index if not exists DbNoteConnectorTableIdx01 on DbNoteConnectorTable (tableId);

create table if not exists DbNoteConnectorSequence
(
    noteId     text,
    sequenceId text,
    primary key (noteId, sequenceId),
    foreign key (noteId) references DbNote (noteId) on delete cascade,
    foreign key (sequenceId) references DbSequence (sequenceId) on delete cascade
);
create index if not exists DbNoteConnectorSequenceIdx01 on DbNoteConnectorSequence (sequenceId);
