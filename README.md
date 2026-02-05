# Java Login System

使用 Java 撰寫的主控台使用者登入系統

## Introduction

這是一個以 **純 Java（不依賴任何框架）** 撰寫的主控台登入系統，

模擬實務中常見的帳號登入流程，包含帳號驗證、密碼加密、登入失敗次數限制與帳號鎖定等機制。

本專案著重於 **物件導向設計（OOP）** 與 **程式結構劃分**，

將商業邏輯與儲存機制分離，方便未來替換成資料庫或整合 Spring Boot。

---

## Features

- 使用者註冊
- 使用者登入
- 密碼加密（Hash）
- 登入失敗次數累計
- 超過次數自動鎖定帳號
- 登入成功後重置失敗次數
- 修改密碼
- 刪除帳號
- 自訂例外處理（帳號不存在、密碼錯誤、帳號鎖定）

---

## Project Structure

src/  
├── Main.java  
│  
├── domain/  
│ └── User.java  
│  
├── repository/  
│ ├── UserRepository.java  
│ └── InMemoryUserRepository.java  
│  
├── service/  
│ └── AuthService.java  
│  
├── policy/  
│ ├── PasswordPolicy.java  
│ ├── DefaultPasswordPolicy.java  
│ ├── AccountPolicy.java  
│ └── DefaultAccountPolicy.java  
│  
├── security/  
│ ├── PasswordEncoder.java  
│ └── SHA256PasswordEncoder.java  
│  
└── exception/  
├── AccountNotFoundException.java  
├── AccountLockedException.java  
├── InvalidAccountException.java  
├── PasswordMismatchException.java  
│  
├── session/  
└── LoginSession.java 

---

## How to Run

1. 下載專案  
   https://github.com/rennychen/login.system.git

2. 編譯程式
   javac src/Main.java

3. 執行程式
   java Main

4.依照主控台選單操作（註冊 / 登入 / 修改密碼 / 刪除帳號）

---

## 使用技術

設計說明（OOP）
本專案以** 物件導向設計 **為核心，並刻意避免框架依賴：

- User

  負責帳號狀態（密碼、登入失敗次數、是否鎖定）

  封裝登入失敗與鎖定邏輯

- Repository Pattern

  UserRepository 為介面

  InMemoryUserRepository 為記憶體實作

  未來可無痛替換為資料庫實作

- Service Layer

  AuthService 專注於登入與驗證流程

  不關心資料儲存細節

- Policy Pattern

  PasswordPolicy、AccountPolicy 定義規則介面

  預設實作集中於 DefaultXXXPolicy

  規則可獨立替換，不影響主流程

- Exception Handling

  使用自訂 RuntimeException

  清楚區分錯誤原因，避免回傳模糊的錯誤訊息

  ---

  ## 備註

本專案為 純 Java 版本，目的在於打好系統設計與商業邏輯基礎。

未來將另開 Repository，實作：

- Spring Boot

- Maven

- Database（JPA / JDBC）

以展示從「核心邏輯」到「實務應用」的完整轉換流程。 
