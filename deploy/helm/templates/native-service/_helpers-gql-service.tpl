{{- define "spring-native-service.selectorLabels" -}}
app: spring-native-service
release: {{ .Release.Name }}
{{- end }}

{{- define "spring-native-service.labels" -}}
chart: {{ include "app.chart" . }}
{{ include "spring-native-service.selectorLabels" . }}
heritage: {{ .Release.Service }}
app: spring-native-service
{{- end }}