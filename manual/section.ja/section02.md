### （section02）テーブルの作成

プロジェクト作成直後は、何もない状態となっています。  

![](../image/03_Main_01.png)

ここで、右側の罫線がかかった部分（ワークスペース）で、右クリックをして、  
「New table」を選択します。  

![](../image/03_Main_02.png)

右クリックした位置に、新規にテーブルのオブジェクトが作成されます。  
テーブルオブジェクトの薄赤色の部分をダブルクリックすると、テーブルの詳細入力画面が開きます。  

![](../image/04_Table_01.png)

「Table name」は、いわゆる物理名で、テーブルの名前を入力します。  
「Table comment」は、いわゆる論理名で、テーブルのコメントを入力します。  
「Engin」「Charset」「Collate」は、`<Default>` にしておくことで、全体設定が適用されます。  
個別に変更する場合は、入力もしくは選択してください。  
「Auto increment」は、初期値となります。  
「Option」はテーブル構築時のオプションとなります。

次にカラムの設定をします。  
「Column name」のセルをダブルクリックすると、カラム名の入力もしくはすでに存在しているカラムの情報を参照できます。  

![](../image/04_Table_02_ColumnName.png)

初期時点では、カラムが存在していないため、カラム名を入力して __Ok__ を押下します。  

![](../image/04_Table_03.png)

カラム名がセルに設定された状態なるので、他の項目を入力・選択していきます。  

![](../image/04_Table_04.png)

さらにカラムを追加します。  

![](../image/04_Table_05.png)

次に主キーの設定をします。  

「primary key」のタブの「Columns and collations」のセルをダブルクリックします。  

![](../image/04_Table_06_PrimaryKey.png)

「Seq in index」で、キーの順番を指定し、 __Ok__ ボタンを押下します。  

![](../image/04_Table_07.png)

これで、主キーがセットされた状態となりました。  
「Index comment」と「Index type」は任意入力です。  

次にユニークキーの設定をします。  

![](../image/04_Table_08.png)

「unique key」のタブの「Columns and collations」のセルをダブルクリックします。  

![](../image/04_Table_09_UniqueKey.png)

「Seq in index」で、キーの順番を指定し、 __Ok__ ボタンを押下します。  

![](../image/04_Table_10.png)

これで、ユニークキーがセットされた状態となりました。  
「Key name」は、未入力の場合、保存時にカラム名がセットされます。  
「Index comment」と「Index type」は任意入力です。  

 __Save and close__ を押下すると、  
テーブルの情報がワークスペース上に表示されます。  

![](../image/05_Main_01.png)

---

[（section03）シーケンスの作成](section03.md)

[一覧に戻る](../manual.ja.md)