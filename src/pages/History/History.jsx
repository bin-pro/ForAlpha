import React, { useState, useEffect } from "react";
import { NavBar } from "../../components/NavBar";
import { NoOfItemsWrapper } from "../../components/NoOfItemsWrapper";
import { TabBarItem } from "../../components/TabBarItem";
import { Toggle } from "../../components/Toggle";
import { Icon11 } from "../../icons/Icon11";
import { Icon8 } from "../../icons/Icon8";
import { Icon9 } from "../../icons/Icon9";
import { Icon14 } from "../../icons/Icon14";
import { LeftButton } from "../../icons/LeftButton";
import { ContentSwitcher } from "../../components/ContentSwitcher";
import ReactApexChart from "react-apexcharts";
import axios from "axios";
import "./style.css";

export const History = () => {
  const [selectedTab, setSelectedTab] = useState("section1"); // 초기 탭 "예측 내역"
  const [HistoryData, setHistoryData] = useState([]);
  const [refreshing, setRefreshing] = useState(false);

  const donutData = {
    series: [50,40,30,10,0],
    options: {
      chart: {
        type: 'donut',
      },
      legend: {
        position: 'bottom'
      },
      responsive: [{
        breakpoint: 480,
      }],
      plotOptions: {
        pie: {
          donut: {
            size: "70%",
            labels: {
              show: true,
              total: {
                showAlways: true,
                show: true,
                label: 'THEME',
                fontSize: '12px',
              },
              value: {
                fontSize: '22px',
                show: true,
              },
            },
          }
        }
      },
      labels: ["금융", "IT", "화학", "유통", "반도체"],
      title: {
        text: '내가 주로 성공한 종목은?',
        align: 'center'
      },
      fill: {
        opacity: 1,
        colors: ["#0066FF", "#7AFFBF", "#00D1FF"],
      },
    },
  }

  const dummyData = [
    {
      created_at: "2023/10/08-2023/10/10",
      earned_point: "+50",
      stock_name: "삼성전자",
      stock_returns: "+5",
      quiz_question: "예제 퀴즈 질문",
      quiz_answer: true,
    },
    {
      created_at: "2023/10/08-2023/10/10",
      earned_point: "+50",
      stock_name: "삼성전자",
      stock_returns: "+5",
      quiz_question: "예제 퀴즈 질문",
      quiz_answer: true,
    },
    {
      created_at: "2023/10/08-2023/10/10",
      earned_point: "+50",
      stock_name: "삼성전자",
      stock_returns: "-5",
      quiz_question: "예제 퀴즈 질문",
      quiz_answer: true,
    },
  ]

  const fetchHistory = async (selectedTab, userUuid) => {
    try {
      const endpoint = selectedTab === "section1" ? "prediction" : "quiz";
      const response = await axios.get(`http://test2.shinhan.site//profile/history/${endpoint}?user-uuid=${userUuid}`);
      let historyData;

      if (selectedTab === "section1") {
        historyData = response.data.payload.predictionHistory;
        console.log(selectedTab);
      } else if (selectedTab === "section2") {
        historyData = response.data.payload.quizHistory;
        console.log(selectedTab);
      }
      setHistoryData(historyData);
      console.log("History loaded");
      console.log("History Data:", historyData);
    } catch (error) {
      console.error("API 요청 실패:", error);
    }
  };

  // useEffect(() => {
  //   const userUuid = "846813a8-d894-4956-9d41-ad6d24292e2c";
  //   fetchHistory(selectedTab, userUuid);
  // }, [selectedTab]);

  useEffect(() => {
    // 더미 데이터를 초기 데이터로 설정
    setHistoryData(dummyData);
  }, []);

  const handleTabChange = (tab) => {
    setSelectedTab(tab);
    fetchHistory(tab);
  };

  return (
    <div className="history">
      <div className="div-2">
        <NavBar
          className="nav-bar-instance"
          hasRightButton={false}
          icon={<LeftButton className="left-button-6" />}
          leftControl="icon"
          pageTitle="History"
          rightButtonClassName="design-component-instance-node"
          rightControl="icon"
        />
        <div className="tab-bar">
                <TabBarItem className="tab-3" icon={<Icon11 className="icon-2" />} selected={false} title="Home" />
                <TabBarItem className="tab-3" icon={<Icon8 className="icon-2" />} selected={false} title="Point" />
                <TabBarItem className="tab-3" icon={<Icon9 className="icon-2" />} selected={false} title="Feed" />
                <TabBarItem className="tab-3" icon={<Icon14 className="icon-2" />} selected={false} title="Profile" />
        </div>
        <div className="comment">
          <div className="frame">
            <div className="div-wrapper">
              <p className="text-wrapper">편식 없이 골고루 투자하는 ‘균형파&#39;</p>
            </div>
            <div className="donut-chart">
                <ReactApexChart 
                    options={donutData.options}
                    series={donutData.series}
                    type="donut" 
                    width="300"
                />
            </div>
            <div className="chart-info">
              
            </div>
          </div>
          <img
            className="divider-3"
            alt="Divider"
            src="https://cdn.animaapp.com/projects/6524a15db6c5edc3e26fb475/releases/6524a452226c039374e07ea9/img/divider-8.svg"
          />
          <div>
            <Toggle section1Text="예측 내역" section2Text="OX 퀴즈 내역" onTabChange={handleTabChange}/>
          </div>
        </div>
        <div className="list">
        {selectedTab === "section1" && HistoryData && HistoryData.length > 0 &&(
          <div className="list-item">
            {HistoryData.map((item, index) => (
              <div key={index} className="content">
                <div className="content">
                  <p className="title">
                    <span className="span">{item.created_at-item.end_day} </span>
                    <span className="text-wrapper-2">{item.earned_point}Point</span>
                  </p>
                  <p className="description">
                    <span className="text-wrapper-3">{item.stock_name}</span>
                    <span className="text-wrapper-4">로 </span>
                    <span className="text-wrapper-5" style={{ color: item.stock_returns < 0 ? 'var(--highlightdarkest)' : 'var(--supporterrordark)' }}>{item.stock_returns}%</span>
                    <span className="text-wrapper-4">를 달성했어요.</span>
                  </p>
                </div>
              </div>
            ))}
          </div>
        )}
        {selectedTab === "section2" && HistoryData && HistoryData.length > 0 && (
          <div className="list-item">
            {HistoryData.map((item, index) => (
              <div key={index} className="content">
                <div className="content">
                  <p className="title">
                    <span className="span">{item.created_at} </span>
                    <span className="text-wrapper-2">{item.quiz_point}Point</span>
                  </p>
                  <p className="description">
                    <span className="text-wrapper-3">{item.quiz_question}</span>
                    <span className="text-wrapper-4">{item.quiz_answer ? " ⭕️" : " ❌"}</span>
                  </p>
                </div>
              </div>
            ))}
          </div>
        )}
        </div>
      </div>
    </div>
  );
};