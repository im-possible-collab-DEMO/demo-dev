import { Link } from 'react-router-dom';

function BottomNav() {
  return (
    <nav>
      <Link to="/main">홈</Link>
      <Link to="/assets">자산</Link>
      <Link to="/transactions">거래내역</Link>
      <Link to="/budget">예산</Link>
      <Link to="/report">리포트</Link>
      <Link to="/mypage">마이페이지</Link>
    </nav>
  );
}

export default BottomNav;