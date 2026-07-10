import SummaryCard from '../components/Main/SummaryCard';
import RecentTransaction from '../components/Main/RecentTransaction';
import QuickMenu from '../components/Main/QuickMenu';
import BottomNav from '../components/Main/BottomNav';

function Main() {
  return (
    <div>
      <h1>메인</h1>
      <SummaryCard />
      <QuickMenu />
      <RecentTransaction />
      <BottomNav />
    </div>
  );
}

export default Main;