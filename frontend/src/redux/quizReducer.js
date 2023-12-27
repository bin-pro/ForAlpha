const initialState = {
    quizText: "",
    quizAnswer: "",
    quizExplanation: "",
  };
  
  const quizReducer = (state = initialState, action) => {
    switch (action.type) {
      case "SET_QUIZ":
        return {
          ...state,
          quizText: action.quizText,
          quizAnswer: action.quizAnswer,
          quizExplanation: action.quizExplanation,
        };
      default:
        return state;
    }
  };
  
  export default quizReducer;  