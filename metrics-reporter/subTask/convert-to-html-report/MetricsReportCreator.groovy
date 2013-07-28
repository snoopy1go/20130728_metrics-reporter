import groovy.util.slurpersupport.GPathResult
import groovy.xml.MarkupBuilder

import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.StringWriter



/**
 * Eclipse-Metrics-PluginのHTMLレポートを生成するクラス。
 * @author takeyuki nagai
 * @since 3.0
 */
class MetricsReportCreator {

    /**
     * メインメソッド
     * @param args 引数（無し）
     */
    public static void main(String[] args) {
        writeReport(buildReportHtml(loadConfig(), parseMetricsXmlReport()))
    }

    /**
     * 設定情報をロードする。
     * @return 設定情報オブジェクト
     */
    private static ConfigObject loadConfig() {
        return new ConfigSlurper().parse(new File("./config.groovy").toURI().toURL())
    }

    /**
     * Eclipse-Metrics-Pluginのレポート（xml）を解析する。
     * @return 解析結果(GPath表現)
     */
    private static GPathResult parseMetricsXmlReport() {
        return new XmlSlurper().parse( new InputStreamReader(new FileInputStream("../../work/metrics-result.xml"), "Shift_JIS"))
    }

    /**
     * HTMLレポートを組み立てる。
     * @param config 設定情報
     * @param metricsResult Eclipse-Metrics-Pluginのレポート（xml）の解析結果
     * @return 組み立て済みのHTMLコンテンツ
     */
    private static String buildReportHtml(ConfigObject config, GPathResult metricsResult) {
        def sw = new StringWriter()
        def html = new MarkupBuilder(sw)

        def singleMetricsTbl = createSingleMetricsTblClosure(html)
        html.html{
            head{
                title("メトリクス基準違反一覧")
            }
            body{
                h2("メトリクス基準違反一覧")
                singleMetricsTbl(listBadMetricsInfo("NORM", config.norm.allowedMax, metricsResult), config.norm.description, config.norm.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("NOF", config.nof.allowedMax, metricsResult), config.nof.description, config.nof.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("NSC", config.nsc.allowedMax, metricsResult), config.nsc.description, config.nsc.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("NOC", config.noc.allowedMax, metricsResult), config.noc.description, config.noc.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("MLOC", config.mloc.allowedMax, metricsResult), config.mloc.description, config.mloc.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("NOM", config.nom.allowedMax, metricsResult), config.nom.description, config.nom.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("NBD", config.nbd.allowedMax, metricsResult), config.nbd.description, config.nbd.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("DIT", config.dit.allowedMax, metricsResult), config.dit.description, config.dit.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("NOP", config.nop.allowedMax, metricsResult), config.nop.description, config.nop.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("CA", config.ca.allowedMax, metricsResult), config.ca.description, config.ca.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("NOI", config.noi.allowedMax, metricsResult), config.noi.description, config.noi.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("VG", config.vg.allowedMax, metricsResult), config.vg.description, config.vg.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("TLOC", config.tloc.allowedMax, metricsResult), config.tloc.description, config.tloc.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("RMI", config.rmi.allowedMax, metricsResult), config.rmi.description, config.rmi.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("PAR", config.par.allowedMax, metricsResult), config.par.description, config.par.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("LCOM", config.lcom.allowedMax, metricsResult), config.lcom.description, config.lcom.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("CE", config.ce.allowedMax, metricsResult), config.ce.description, config.ce.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("NSM", config.nsm.allowedMax, metricsResult), config.nsm.description, config.nsm.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("RMD", config.rmd.allowedMax, metricsResult), config.rmd.description, config.rmd.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("RMA", config.rma.allowedMax, metricsResult), config.rma.description, config.rma.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("SIX", config.six.allowedMax, metricsResult), config.six.description, config.six.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("WMC", config.wmc.allowedMax, metricsResult), config.wmc.description, config.wmc.allowedMax)
                singleMetricsTbl(listBadMetricsInfo("NSF", config.nsf.allowedMax, metricsResult), config.nsf.description, config.nsf.allowedMax)
            }
        }
        return sw.toString()
    }

    /**
     * 単一メトリクスの情報テーブル（html要素）を表現するクロージャを生成する。
     * @param builderToDelegate 委譲対象のビルダー
     * @return 単一メトリクスの情報テーブル（html要素）を表現するクロージャ
     */
    private static Closure createSingleMetricsTblClosure(MarkupBuilder builderToDelegate) {
        def singleMetricsTbl = {badMericsInfo, description, allowedMax ->
            if (badMericsInfo.size() == 0) {
                return
            }
            b(description)
            table(border:1, cellspacing:0, bordercolor:"#DCDCDC"){
                tr{
                    th("package")
                    th("source")
                    th("name")
                    th("metric value" + "(NG: > " + allowedMax + ")")
                }
                badMericsInfo.each { metricVal ->
                    tr{
                        td(metricVal.@package)
                        td(metricVal.@source)
                        td(metricVal.@name)
                        td(metricVal.@value)
                    }
                }
            }
            br()
        }
        singleMetricsTbl.delegate = builderToDelegate
        return singleMetricsTbl
    }

    /**
     * 指定されたメトリクスの集計結果から、規定に違反しているものを一覧する。
     * @param metricsId メトリクスを識別するID
     * @param allowedMaxVal metricsIdに対応するメトリクスの許容値
     * @param metricsResult Eclipse-Metrics-Pluginのレポート（xml）の解析結果
     * @return
     */
    private static GPathResult listBadMetricsInfo(String metricsId, BigDecimal allowedMaxVal, GPathResult metricsResult) {
        return metricsResult.Metric.find{it.@id == metricsId}
                            .Values
                            .Value.findAll{new BigDecimal(it.@value.toString()).compareTo(new BigDecimal(allowedMaxVal)) > 0}
    }

    /**
     * レポートをディスクに書き出す。
     * @param reportContents レポートの中身
     */
    private static void writeReport(String reportContents) {
        def writer;
        try {
            writer = new OutputStreamWriter(new FileOutputStream("../../report/metrics.html"), "UTF-8")
            writer.write(reportContents)
        } finally {
            if (writer != null) {
                writer.close()
            }
        }
    }
}
