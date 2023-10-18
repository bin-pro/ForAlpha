import "./styles/reset.css";
import "./styles/global.css";
import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Intro } from "./pages/Intro";
import { Login } from "./pages/Login";
import { Signup } from "./pages/Signup";
import Signup2 from "./pages/Signup2/Signup2";

import { FindPassword } from "./pages/FindPassword";
import { Home } from "./pages/Home";
import { Feed } from "./pages/Feed";
import { Friends } from "./pages/Friends";
import { PointHome } from "./pages/PointHome";
import { Prediction } from "./pages/Prediction";

import { StockSearch } from "./pages/StockSearch";
import { Complete } from "./pages/Complete";
import { Quiz } from "./pages/Quiz";
import { Answer } from "./pages/Answer";
import { Profile } from "./pages/Profile";
import { Card } from "./pages/Card";
import { History } from "./pages/History";

function App() {
  return (
    <div>
      <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/signup" element={<Signup2 />} />
        <Route path="/password" element={<FindPassword />} />
        <Route path="/home" element={<Home />} />
        <Route path="/feed" element={<Feed />} />
        <Route path="/friends" element={<Friends />} />
        <Route path="/point-home" element={<PointHome />} />
        <Route path="/prediction" element={<Prediction />} />
        <Route path="/stock-search" element={<StockSearch />} />
        <Route path="/complete" element={<Complete />} />
        <Route path="/quiz" element={<Quiz />} />
        <Route path="/answer" element={<Answer />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/card" element={<Card />} />
        <Route path="/history" element={<History />} />
        <Route path="*" element={<div>페이지를 찾을 수 없습니다</div>} />
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
