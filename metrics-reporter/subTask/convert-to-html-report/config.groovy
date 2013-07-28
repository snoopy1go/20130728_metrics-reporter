norm {
  allowedMax = 2
  description = "親クラスのメソッドをオーバーライドしたメソッド数(NORM)"
}
nof {
  allowedMax = 5
  description = "クラス中のフィールド数(NOF)"
}
nsc {
  allowedMax = 1
  description = "クラスの持つサブクラスの数(NSC)"
}
noc {
  allowedMax = 20
  description = "Javaファイル中のクラス数(NOC)"
}
mloc {
  allowedMax = 50
  description = "メソッドのコード行数（コメント行・空行は含まない）(MLOC)"
}
nom{
  allowedMax = 7
  description = "クラス中のメソッド数(NOM)"
}
nbd{
  allowedMax = 3
  description = "メソッド中の最大のネスト数(NBD)"
}
dit{
  allowedMax = 3
  description = "Java.lang.Objectクラスからの継承の深さ(DIT)"
}
nop{
  allowedMax = 5
  description = "パッケージ数(NOP)"
}
ca{
  allowedMax = 5
  description = "パッケージ内のクラスに依存するパッケージ外のクラス数(CA)"
}
noi{
  allowedMax = 0
  description = "Javaファイル中のインターフェイス数(NOI)"
}
vg{
  allowedMax = 5
  description = "メソッドのサイクロマチック数(VG)"
}
tloc{
  allowedMax = 100
  description = "クラスのコード行数（コメント行・空行は含まない）(TLOC)"
}
rmi{
  allowedMax = 0.5
  description = "パッケージの不安定性（算出式：CE / (CA + CE)）(RMI)"
}
par{
  allowedMax = 3
  description = "メソッドのパラメータ数(PAR)"
}
lcom{
  allowedMax = 0.5
  description = "クラス中のメソッドの凝集性欠如の度合い(LCOM)"
}
ce{
  allowedMax = 1
  description = "パッケージ外のクラスに依存するパッケージ内のクラス数(CE)"
}
nsm{
  allowedMax = 1
  description = "クラス中の静的メソッドの数(NSM)"
}
rmd{
  allowedMax = 0.5
  description = "パッケージの主系列（RMA + RMI == 1）からの標準化された距離（算出式：|RMA + RMI ? 1|）(RMD)"
}
rma{
  allowedMax = 0.3
  description = "パッケージの抽象度（パッケージ内の抽象クラス・インターフェイスの割合）(RMA)"
}
six{
  allowedMax = 1
  description = "クラスの特殊化指標の平均（算出式：(NORM * DIT) / NOM）(SIX)"
}
wmc{
  allowedMax = 10
  description = "クラス中のすべてのメソッドのサイクロマチック数（VG）の和(WMC)"
}
nsf{
  allowedMax = 0
  description = "クラス中の静的フィールド数(NSF)"
}