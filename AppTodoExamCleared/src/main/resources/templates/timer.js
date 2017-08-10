
var timer; //タイマーを格納する変数（タイマーID）の宣言

//カウントダウン関数を1000ミリ秒毎に呼び出す関数
function cntStart()
{
  document.timer.elements[2].disabled=true;
  timer1=setInterval("countDown()",1000);
}

//タイマー停止関数
function cntStop()
{
  document.timer.elements[2].disabled=false;
  clearInterval(timer1);
}

//カウントダウン関数
function countDown()
{
  var min=document.timer.elements[0].value;
  var sec=document.timer.elements[1].value;
  
  if( min==""　)
  {
	  if( sec=="" ) {
    		alert("時刻を設定してください！");
   			resetForm();
	  }
  }
  else
  {
	//判定チェック
    if (min=="") min=0;
    min=parseInt(min);
    
    if (sec=="") sec=0;
    sec=parseInt(sec);
    
    //チェックする
    m = min*60+sec-1
    if (num<=parseInt(m))
    {
      resetForm();
      show();
    }
    else
    {
      //残り分数はintを60で割って切り捨てる
      document.timer.elements[0].value=Math.floor(num/60);
      //残り秒数はintを60で割った余り
      document.timer.elements[1].value=num % 60;
    }
  }
}

//フォームを初期状態に戻す（リセット）関数
function resetForm()
{
  document.timer.elements[0].value="0";
  document.timer.elements[1].value="0";
  document.timer.elements[2].disabled=false;
  clearInterval(timer1);
}  