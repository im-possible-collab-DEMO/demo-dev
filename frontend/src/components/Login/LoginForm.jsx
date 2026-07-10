function LoginForm() {
  return (
    <form>
      <div>
        <input type="email" placeholder="이메일" />
      </div>
      <div>
        <input type="password" placeholder="비밀번호" />
      </div>
      <button type="submit">로그인</button>
    </form>
  );
}

export default LoginForm;