{{/* vim: set filetype=mustache: */}}
{{/*
Expand the name of the chart.
*/}}
{{- define "palindrome-app.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate to 20 characters because this is used to set the node identifier in WildFly which is limited to
23 characters. This allows for a replica suffix for up to 99 replicas.
*/}}
{{- define "palindrome-app.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 20 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 20 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 20 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "palindrome-app.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "palindrome-app.labels" -}}
helm.sh/chart: {{ include "palindrome-app.chart" . }}
{{ include "palindrome-app.selectorLabels" . }}
app.kubernetes.io/version: {{ .Values.image.tag | default .Chart.AppVersion | trunc 63 | quote }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "palindrome-app.selectorLabels" -}}
app.kubernetes.io/name: {{ include "palindrome-app.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "palindrome-app.serviceAccountName" -}}
{{- if .Values.serviceAccount.create }}
{{- default (include "palindrome-app.fullname" .) .Values.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.serviceAccount.name }}
{{- end }}
{{- end }}

{{/*
Create a default fully qualified app name for the postgres requirement.
*/}}
{{- define "palindrome-app.postgresql.fullname" -}}
{{- $postgresContext := dict "Values" .Values.postgresql "Release" .Release "Chart" (dict "Name" "postgresql") -}}
{{ include "palindrome-app.fullname" .}}-{{ include "postgresql.name" $postgresContext }}
{{- end }}

{{/*
Create the service DNS name.
*/}}
{{- define "palindrome-app.serviceDnsName" -}}
{{ include "palindrome-app.fullname" . }}-headless.{{ .Release.Namespace }}.svc.{{ .Values.clusterDomain }}
{{- end }}
