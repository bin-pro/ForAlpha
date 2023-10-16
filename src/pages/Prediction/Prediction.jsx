import React from "react";
import { ButtonPrimary } from "../../components/ButtonPrimary";
import { Divider } from "../../components/Divider";
import { NavBar } from "../../components/NavBar";
import { NoOfItemsWrapper } from "../../components/NoOfItemsWrapper";
import { ShoppingCartItem } from "../../components/ShoppingCartItem";
import { SearchBar } from "../../components/SearchBar";
import { TabBarItem } from "../../components/TabBarItem";
import { Icon9 } from "../../icons/Icon9";
import { Icon10 } from "../../icons/Icon10";
import { Icon11 } from "../../icons/Icon11";
import { Icon13 } from "../../icons/Icon13";
import { Search4 } from "../../icons/Search4";
import { LeftButton } from "../../icons/LeftButton";
import { RightButton3 } from "../../icons/RightButton3";
import { BiSearch} from 'react-icons/bi';
import ReactApexChart from 'react-apexcharts';
import Swal from "sweetalert2";
import "./style.css";

export const Prediction = () => {
    const [stockname, setStockName] = React.useState("");
    const [predictDay, setPredictDay] = React.useState("");

    const data = [
        {
          time_close: '2023-10-13T09:00:00',
          open: 100,
          high: 110,
          low: 95,
          close: 105,
        },
        {
          time_close: '2023-10-13T10:00:00',
          open: 105,
          high: 115,
          low: 100,
          close: 110,
        },
      ];
    
      const handleOrderClick = () => {
        // 주문 버튼 클릭
        Swal.fire({
          title: "주문 확인",
          text: "주문하시겠습니까?",
          icon: "question",
          showCancelButton: true,
          confirmButtonText: "주문",
          cancelButtonText: "취소",
        }).then((result) => {
          if (result.isConfirmed) {
            // 확인 버튼이 클릭되면 주문을 처리
            Swal.fire("주문 완료", "주문이 정상적으로 처리되었습니다.", "success");
            // 이곳에 주문 제출 로직을 추가하세요.
          }
        });
      };

  return (
    <div className="prediction">
        <div className="predict-frame">
          <NavBar
                  className="nav-bar-instance"
                  hasDiv={false}
                  hasRightButton={false}
                  leftControl="none"
                  pageTitle="상승할 주식 예측하기"
                  rightButtonClassName="nav-bar-2"
                  rightControl="none"
              />
            <div className="text-field-instance">
                <input className="input-field-stock" type="text" name="stockname" value={stockname} placeholder="search" />
                <BiSearch className="searchbar-icon" />
                <button className="order-btn" onClick={handleOrderClick}>주문</button>
            </div>
            <div className="stock-field-instance">
                <div className="stock">신한지주</div>
                <div className="stock-price">66,000</div>
            </div>
            <Divider className="divider-7" />
            <div className="subtitle">일</div>
            <div className="stock-chart">
                <ReactApexChart
                    type="candlestick"
                    series={[
                        {
                        data: data?.map(price => ({
                            x: price.time_close,
                            y: [price.open, price.high, price.low, price.close],
                        })),
                        },
                    ]}
                    options={{
                        theme: {palette: 'palette1'},
                        chart: {
                        height: 350,
                        width: 500,
                        toolbar: {
                            show: false,
                            tools: {},
                        },
                        background: "transparent",
                        },
                        grid: {
                        show: false,
                        },
                        plotOptions: {
                        candlestick: {
                            wick: {
                            useFillColor: true,
                            },
                        },
                        },
                        xaxis: {
                            type: "datetime",
                            categories: data?.map((price) => price.time_close),
                            labels: {
                                style: {
                                    colors: '#124cf5'
                                }
                        },
                        type: "datetime",
                        categories: data?.map(date => date.time_close),
                        axisBorder: {
                            show: false,
                        },
                        axisTicks: {
                            show: false,
                        },
                        },
                        yaxis: {
                        show: false,
                        },
                        tooltip: {
                        y: {
                            formatter: v => `$ ${v.toFixed(2)}`,
                        },
                        },
                        plotOptions: {
                            candlestick: {
                              colors: {
                                upward: '#3C90EB',
                                downward: '#DF7D46'
                              }
                            }
                          }
                    }}
                    />
                </div>
            <Divider className="divider-7-instance" />
            <div className="point-notice">
                <div className="custom-day">
                    <input className="input-field-day" type="text" name="prdictDay" value={predictDay} placeholder="day" />
                    일 후에 가격이 오른다면,
                </div>
                <div>120 + α 포인트를 얻을 수 있어요</div>
            </div>
            <div className="tab-bar">
                <TabBarItem className="tab-3" icon={<Icon11 className="icon-3" />} selected={false} title="Home" />
                <TabBarItem className="tab-bar-item-instance" icon={<Icon13 className="icon-3" />} selected tabNameClassName="tab-2" title="Point"/>
                <TabBarItem className="tab-3" icon={<Icon9 className="icon-3" />} selected={false} title="Feed" />
                <TabBarItem className="tab-3" icon={<Icon10 className="icon-3" />} selected={false} title="Profile" />
            </div>
        </div>
    </div>
  );
};
