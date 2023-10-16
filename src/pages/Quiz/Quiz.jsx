import React, { useState, useEffect } from "react";
import { NavBar } from "../../components/NavBar";
import { TabBarItem } from "../../components/TabBarItem";
import { ButtonPrimary } from "../../components/ButtonPrimary";
import { Icon7 } from "../../icons/Icon7";
import { Icon8 } from "../../icons/Icon8";
import { Icon9 } from "../../icons/Icon9";
import { Icon10 } from "../../icons/Icon10";
import { LeftButton } from "../../icons/LeftButton";
import axios from "axios";
import "./style.css";

export const Quiz = () => {
  const [question, setQuestion] = useState("");
  const [selectedAnswer, setSelectedAnswer] = useState(null);
  const [quizId, setQuizId] = useState(null);

  useEffect(() => {
    fetchQuestion();
  }, []);

  const fetchQuestion = async () => {
    try {
      const response = await axios.get("http://test2.shinhan.site:8002/foralpha-service/point/quiz");
      const questionText = response.data.quiz_question;
      const quizId = response.data.id;
      setQuestion(questionText);
      setQuizId(quizId);
      console.log("Quiz question loaded");
    } catch (error) {
      console.error("Failed to fetch question:", error);
    }
  };

  const handleButtonClick = async (choice) => {
    setSelectedAnswer(choice);

    try {
      const answerResponse = await axios.get(`http://test2.shinhan.site:8002/foralpha-service/point/quiz/answer?quiz-uuid=${quizId}`);
      const answer = answerResponse.data.quiz_answer;
      const explain = answerResponse.data.quiz_explanation;
      const isCorrect = choice === answer;

      await axios.post("http://test2.shinhan.site:8002/foralpha-service/point/quiz", {
        quizId,
        userId: "user-id",
        selectedAnswer: choice,
      });
      
      // 다음 질문을 가져올 수 있도록 fetchQuestion 함수 호출
      fetchQuestion();
    } catch (error) {
      console.error("Failed to send user choice:", error);
    }
  };

  return (
    <div className="quiz">
    <div className="div-2">
      <div className="overlap-group">
        <div className="feed">
          <div className="products">
            <div className="vertical-card-wrapper">
              <div className="vertical-card">
                <div className="frame" />
                <div className="content">
                  <div className="title">
                    <p className="question">
                      {question}
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="products-wrapper">
            <div className="products-2">
              <ButtonPrimary
                className="button-primary-instance"
                divClassName="design-component-instance-node"
                text="O"
                onClick={() => handleButtonClick("O")}
              />
              <ButtonPrimary
                className="button-primary-instance-2"
                divClassName="design-component-instance-node"
                text="X"
                onClick={() => handleButtonClick("X")}
              />
            </div>
          </div>
        </div>
        <div className="tab-bar">
                <TabBarItem className="tab-3" icon={<Icon7 className="icon-2" />} selected={false} title="Home" />
                <TabBarItem className="tab-3" icon={<Icon8 className="icon-2" />} selected={false} title="Point" />
                <TabBarItem className="tab-3" icon={<Icon9 className="icon-2" />} selected={false} title="Feed" />
                <TabBarItem className="tab-3" icon={<Icon10 className="icon-2" />} selected={false} title="Profile" />
            </div>
        </div>
      <NavBar
        className="nav-bar-instance"
        hasRightButton={false}
        icon={<LeftButton className="left-button-4" />}
        leftControl="icon"
        pageTitle="OX퀴즈"
        rightButtonClassName="nav-bar-2"
        rightControl="none"
        leftLink="/point-home"
      />
    </div>
  </div>
  );
};