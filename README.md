# リポジトリをcloneし、アプリを実行するための手順

## ソフトウェアのインストール

### Windows
#### Chocolatey
#### 1.Windows PowerShellを管理者として起動し、以下のコマンドを実行する。
```powershell
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = 
[System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))
```

#### 2.コマンドプロンプトを管理者として起動し、以下のコマンドを実行する。  
```bat
choco install -y git  
choco install -y corretto21jdk  
choco install -y maven  
choco install -y docker-desktop  
choco install -y vscode  
choco install -y pnpm  
choco install -y nodejs-lts
```

<br>

### macOS
#### Homebrew
#### 1.ターミナルを起動し、以下のコマンドを実行する。
```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install.sh)"
```

#### 2.ターミナルを起動し、以下のコマンドを実行する。
```bash
brew install git  
brew install corretto@21  
brew install maven  
brew install docker --cask  
brew install visual-studio-code --cask  
brew install pnpm  
brew install nodebrew
```

#### 3.nodebrewの初期設定をする。
```bash
nodebrew setup 　
echo 'export PATH=$PATH:$HOME/.nodebrew/current/bin' >> ~/.bash_profile
```

#### 4.使用するバージョンをセットする。
事前にLTSバージョンを確認しておく (https://github.com/nodejs/Release?tab=readme-ov-file#release-schedule)

#### 5.nodeのインストール＋使用設定をする。また、target versionはLTSバージョンに置き換える。
```bash
nodebrew install-binary <target version>
nodebrew use <target version>
```

#### 6.nodeコマンドでバージョン確認する。
```bash
node -v
```

<br><br>

## リポジトリのcloneとその他のインストール

#### 1.Git Bashを開き、以下のコマンドを入力し、プロジェクトのクローンを行う。
```bash
git clone https://github.com/yugo-tamura/monocrea-technical-training.git
```

#### 2.以下のコマンドを入力し、mono-frontディレクトリに移動する。
```bash
cd monocrea-technical-training/mono-front
```

#### 3.以下のコマンドを入力し、json-serverをインストールする。
```bash
npm install -g json-server@0.17.4
```

#### 4.以下のコマンドを入力し、依存関係をインストールする。
```bash
pnpm install
```

<br><br>

## Docker上へのpostgreSQLの構築

#### 1.Docker Desktopを起動し、Git Bashのターミナルを開く。
<img width="956" height="516" alt="image" src="https://github.com/user-attachments/assets/036e6d1c-271c-4c64-a003-5062d0cd69be" />

#### 2.以下のコマンドを入力し、dockerディレクトリに移動する。
```bash
cd monocrea-technical-training\mono-back\src\main\docker
```

#### 3.以下のコマンドを入力し、Dockerコンテナを作成し、起動する。
```bash
docker compose up -d
```

#### 4.接続テストを行う。
```bash
docker exec -it docker-db-1 pg_isready -h localhost -p 5432 -U appuser
```

accepting connections が表示されることを確認する。

<br><br>

## Frontendアプリ起動手順

#### 1.VSCodeなどのテキストエディタで、以下のパスのファイルを開き、該当の行をコメントアウトする

##### monocrea-technical-training/mono-front/src/routes/+page.server.ts

40行目~46行目
```ts
const query = new URLSearchParams({
	condition: keywordQuery.key,
	keyword: keywordQuery.val,
	sort: sortVal.sort,
	order: sortVal.order,
}).toString();
const res = await fetch(`http://localhost:8080/users?${query}`);
```

##### monocrea-technical-training/mono-front/src/routes/detail/[userId]/+page.server.ts

10行目
```ts
const res = await fetch(`http://localhost:8080/users/${params.userId}`);
```

31行目~35行目
```ts
const res = await fetch(`http://localhost:8080/users`, {
	method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ name })
});
```

53行目~57行目
```ts
const res = await fetch(`http://localhost:8080/users/${id}`, {
  method: "PUT",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ id, name })
});
```

73行目~75行目
```ts
const res = await fetch(`http://localhost:8080/users/${id}`, {
  method: "DELETE"
});
```

#### 2.VSCodeなどのテキストエディタで、以下のパスのファイルを開き、該当の行のコメントアウトを外す

##### monocrea-technical-training/mono-front/src/routes/+page.server.ts

27行目~37行目
```ts
const query = new URLSearchParams();
if (keywordQuery.val) {
	if (keywordQuery.key === "id") {
		query.set("id", keywordQuery.val);
	} else if (keywordQuery.key === "name") {
		query.set("name_like", keywordQuery.val);
	}
}
query.set("_sort", sortVal.sort);
query.set("_order", sortVal.order);
const res = await fetch(`http://localhost:3000/users?${query.toString()}`);
```

##### monocrea-technical-training/mono-front/src/routes/detail/[userId]/+page.server.ts

7行目
```ts
const res = await fetch(`http://localhost:3000/users/${params.userId}`);
```

24行目~28行目
```ts
const res = await fetch(`http://localhost:3000/users`, {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ name })
});
```

46行目~50行目
```ts
const res = await fetch(`http://localhost:3000/users/${id}`, {
  method: "PUT",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ name })
});
```

68行目~70行目
```ts
const res = await fetch(`http://localhost:3000/users/${id}`, {
  method: "DELETE"
});
```

#### 3.Git Bashを開き、以下のコマンドを入力してmono-frontディレクトリに移動する。
```bash
cd monocrea-technical-training/mono-front
```

#### 4.以下のコマンドを入力し、json-serverとフロントエンドアプリを起動する。
```bash
npx json-server --watch db.json -p 3000 & pnpm dev --open & wait
```

<br><br>

## Backendアプリ起動手順

#### 1.Docker Desktopを起動し、docker-db-1を起動する。

<img width="958" height="515" alt="image" src="https://github.com/user-attachments/assets/40638f2b-916b-450a-bdb4-2530e9472407" />

<br>

#### 2.VSCodeなどのテキストエディタで、以下のパスのファイルを開き、該当の行をコメントアウトする。

##### monocrea-technical-training/mono-front/src/routes/+page.server.ts

27行目~37行目
```ts
const query = new URLSearchParams();
if (keywordQuery.val) {
	if (keywordQuery.key === "id") {
		query.set("id", keywordQuery.val);
	} else if (keywordQuery.key === "name") {
		query.set("name_like", keywordQuery.val);
	}
}
query.set("_sort", sortVal.sort);
query.set("_order", sortVal.order);
const res = await fetch(`http://localhost:3000/users?${query.toString()}`);
```

##### monocrea-technical-training/mono-front/src/routes/detail/[userId]/+page.server.ts

7行目  
```ts
const res = await fetch(`http://localhost:3000/users/${params.userId}`);
```

24行目~28行目
```ts
const res = await fetch(`http://localhost:3000/users`, {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ name })
});
```

46行目~50行目
```ts
const res = await fetch(`http://localhost:3000/users/${id}`, {
  method: "PUT",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ name })
});
```

68行目~70行目
```ts
const res = await fetch(`http://localhost:3000/users/${id}`, {
  method: "DELETE"
});
```

#### 3.VSCodeなどのテキストエディタで、以下のパスのファイルを開き、該当の行のコメントアウトを外す。

##### monocrea-technical-training/mono-front/src/routes/+page.server.ts

40行目~46行目
```ts
const query = new URLSearchParams({
	condition: keywordQuery.key,
	keyword: keywordQuery.val,
	sort: sortVal.sort,
	order: sortVal.order,
}).toString();
const res = await fetch(`http://localhost:8080/users?${query}`);
```

##### monocrea-technical-training/mono-front/src/routes/detail/[userId]/+page.server.ts

10行目
```ts
const res = await fetch(`http://localhost:8080/users/${params.userId}`);
```

31行目~35行目  
```ts
const res = await fetch(`http://localhost:8080/users`, {
	method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ name })
});
```

53行目~57行目
```ts
const res = await fetch(`http://localhost:8080/users/${id}`, {
  method: "PUT",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ id, name })
});
```

73行目~75行目
```ts
const res = await fetch(`http://localhost:8080/users/${id}`, {
  method: "DELETE"
});
```

#### 4.Git Bashを開き、以下のコマンドを入力してmono-backディレクトリに移動する。
```bash
cd monocrea-technical-training/mono-back
```

#### 5.以下のコマンドを入力し、バックエンドアプリを起動する。
```bash
./mvnw quarkus:dev
```

#### 6.2つ目のGit Bashを開き、以下のコマンドを入力してmono-frontディレクトリに移動する。
```bash
cd monocrea-technical-training/mono-front
```

#### 7.以下のコマンドを入力し、フロントエンドアプリを起動する。
```bash
cd pnpm dev --open
```
